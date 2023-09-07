/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.User;

/**
 *
 * @author prodi
 */
public interface MailService {
    void sendNewQuestionMail(User user);
    void sendQuestionAnswered(User user);
}
