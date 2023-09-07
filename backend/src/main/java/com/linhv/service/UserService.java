/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author prodi
 */
public interface UserService extends UserDetailsService{
    User getUserByEmail(String email);
    User getUserById(int id);
    User addUserByAdmin(User user);
    User addUser(Map<String, String> params, MultipartFile avatar);
    boolean authUser(String email, String password);
    boolean updateUser(Map<String, String> params);
    boolean updateUserByAdmin(User user);
    List<User> getAllUser(Map<String, String> params);
    Long countAll();
}
