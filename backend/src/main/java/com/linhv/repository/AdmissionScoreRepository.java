/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.AdmissionScore;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface AdmissionScoreRepository {
    List<Integer> getAllAdmissionYear();
    List<AdmissionScore> getAll();
    List<AdmissionScore> get5ByFaculty(int id);
    List<AdmissionScore> getAllByFaculty(int id);
    AdmissionScore getFacultyScoreByYear(int fId, int year);
    boolean add(AdmissionScore as);
    boolean update(AdmissionScore as);
    boolean delete(AdmissionScore as);
    boolean isFacultyYearScoreExist(int fId, int year);
}
