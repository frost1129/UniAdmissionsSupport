/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.AdmissionType;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface AdmissionTypeService {
    List<AdmissionType> getAdmissionType();
    AdmissionType getById(int id);
}
