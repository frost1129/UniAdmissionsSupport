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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Override
    public User getUserByEmail(String email) {
        return this.userRepo.getUserByEmail(email);
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
        u.setUserRole(User.USER);
        
//        if (params.get("role").isEmpty())
//            u.setUserRole(User.USER);
//        else 
//            u.setUserRole(params.get("role"));
        
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
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
