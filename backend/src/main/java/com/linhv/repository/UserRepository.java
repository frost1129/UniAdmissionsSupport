/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prodi
 */
public interface UserRepository {
    User getUserByEmail(String email);
    User getUserById(int id);
    User addUser(User user);
    boolean authUser(String email, String password);
    boolean updateUser(User user);
    List<User> getAllUser(Map<String, String> params);
    List<User> getAllUserByAdmissionType(int admissionId);
    Long countAll();
}
