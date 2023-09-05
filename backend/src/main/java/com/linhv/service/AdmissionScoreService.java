/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.AdmissionScore;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface AdmissionScoreService {
    List<Integer> getAllAdmissionYear();
    List<AdmissionScore> getAll();
    List<AdmissionScore> getAllByFaculty(int id);
    AdmissionScore getScoreByYear(int year);
    boolean add(AdmissionScore as);
    boolean update(AdmissionScore as);
    boolean delete(int year);
}
