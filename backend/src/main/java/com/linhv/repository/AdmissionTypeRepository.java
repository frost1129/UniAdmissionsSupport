/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.AdmissionType;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface AdmissionTypeRepository {
    AdmissionType newType(AdmissionType admissionType);
    AdmissionType updateType(AdmissionType admissionType);
    boolean deleteType(AdmissionType admissionType);
    AdmissionType findAdmissionTypeById(int id);
    List<AdmissionType> getAllAdmissionType();
}
