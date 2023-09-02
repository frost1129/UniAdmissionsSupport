/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.QuestionSettings;
import com.linhv.repository.QuestionSettingRepository;
import com.linhv.service.QuestionSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class QuestionSettingServiceImpl implements QuestionSettingService{
    
    @Autowired
    private QuestionSettingRepository settingRepo;
    
    @Override
    public QuestionSettings getTime() {
        return this.settingRepo.getTime();
    }

    @Override
    public boolean setTime(QuestionSettings setting) {
        return this.settingRepo.setTime(setting);
    }

}
