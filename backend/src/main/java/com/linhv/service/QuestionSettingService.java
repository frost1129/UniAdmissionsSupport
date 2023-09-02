/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.QuestionSettings;

/**
 *
 * @author prodi
 */
public interface QuestionSettingService {
    QuestionSettings getTime();
    boolean setTime(QuestionSettings setting);
}
