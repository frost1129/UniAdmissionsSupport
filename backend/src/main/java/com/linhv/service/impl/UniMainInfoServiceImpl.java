/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.UniMainInfo;
import com.linhv.repository.UniMainInfoRepository;
import com.linhv.service.UniMainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class UniMainInfoServiceImpl implements UniMainInfoService{

    @Autowired
    private UniMainInfoRepository uniMainRepo;
    
    @Override
    public UniMainInfo getUniInfo(String id) {
        return this.uniMainRepo.getUniInfo(id);
    }

}
