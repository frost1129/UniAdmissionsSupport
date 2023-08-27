/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.User;

/**
 *
 * @author prodi
 */
public interface UserRepository {
    User getUserByEmail(String email);
    User addUser(User user);
    boolean authUser(String email, String password);
}
