/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.linhv.pojo.User;
import com.linhv.repository.UserRepository;
import com.linhv.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author prodi
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public User getUserByEmail(String email) {
        return this.userRepo.getUserByEmail(email);
    }
    
    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }
    
    @Override
    public User addUserByAdmin(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        user.setActive(true);
        user.setCreatedDate(new Date());
        
        if (!user.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), 
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.userRepo.addUser(user);
        
        return user;
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        User u = new User();
        u.setEmail(params.get("email"));
        u.setPassword(this.encoder.encode(params.get("password")));
        u.setFirstName(params.get("firstName"));
        u.setLastName(params.get("lastName"));
        u.setActive(true);
        u.setCreatedDate(new Date());

//        String userRole = params.get("role");
//        if (userRole != null && !userRole.isEmpty())
//            u.setUserRole(userRole);
//        else 
            u.setUserRole(User.USER);
        
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(), 
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.userRepo.addUser(u);
        
        return u;
    }

    @Override
    public boolean authUser(String email, String password) {
        return this.userRepo.authUser(email, password);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("Không tồn tại tài khoản với Email này!");
        }
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getPassword(), authorities
        );
    }

    @Override
    public boolean updateUser(Map<String, String> params) {
        User user = this.userRepo.getUserByEmail(params.get("email"));
        if (user == null) {
            return false;
        }
        
        user.setLastName(params.get("lastName"));
        user.setFirstName(params.get("firstName"));

        return this.userRepo.updateUser(user);
    }
    
    @Override
    public boolean updateUserByAdmin(User user) {
        User u = this.userRepo.getUserByEmail(user.getEmail());
        if (u == null) {
            return false;
        }
        
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPassword(user.getPassword());
        u.setUserAdmissionType(user.getUserAdmissionType());
        u.setUserRole(user.getUserRole());
        
        return this.userRepo.updateUser(u);
    }

    @Override
    public List<User> getAllUser(Map<String, String> params) {
        return this.userRepo.getAllUser(params);
    }

    @Override
    public Long countAll() {
        return this.userRepo.countAll();
    }

    @Override
    public List<User> getAllUserByAdmissionType(int admissionId) {
        return this.userRepo.getAllUserByAdmissionType(admissionId);
    }
}
