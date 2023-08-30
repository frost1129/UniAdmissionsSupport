/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.AdmissionType;
import com.linhv.repository.AdmissionTypeRepository;
import com.linhv.service.AdmissionTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class AdmissionTypeServiceImpl implements AdmissionTypeService{
    
    @Autowired
    private AdmissionTypeRepository admissionTypeRepo;

    @Override
    public List<AdmissionType> getAdmissionType() {
        return this.admissionTypeRepo.getAllAdmissionType();
    }

}
