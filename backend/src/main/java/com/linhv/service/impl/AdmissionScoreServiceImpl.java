/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.AdmissionScore;
import com.linhv.repository.AdmissionScoreRepository;
import com.linhv.service.AdmissionScoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class AdmissionScoreServiceImpl implements AdmissionScoreService{
    
    @Autowired
    private AdmissionScoreRepository admissionScoreRepo;

    @Override
    public List<Integer> getAllAdmissionYear() {
        return this.admissionScoreRepo.getAllAdmissionYear();
    }

    @Override
    public List<AdmissionScore> getAll() {
        return this.admissionScoreRepo.getAll();
    }
    
    @Override
    public List<AdmissionScore> get5ByFaculty(int id) {
        return this.admissionScoreRepo.get5ByFaculty(id);
    }
    
    @Override
    public List<AdmissionScore> getAllByFaculty(int id) {
        return this.admissionScoreRepo.getAllByFaculty(id);
    }

    @Override
    public AdmissionScore getFacultyScoreByYear(int fId, int year) {
        return this.admissionScoreRepo.getFacultyScoreByYear(fId, year);
    }

    @Override
    public boolean add(AdmissionScore as) {
        return this.admissionScoreRepo.add(as);
    }

    @Override
    public boolean update(AdmissionScore as) {
        return this.admissionScoreRepo.update(as);
    }

    @Override
    public boolean delete(AdmissionScore as) {
        return this.admissionScoreRepo.delete(as);
    }

    @Override
    public boolean isFacultyYearScoreExist(int fId, int year) {
        return this.admissionScoreRepo.isFacultyYearScoreExist(fId, year);
    }
}
