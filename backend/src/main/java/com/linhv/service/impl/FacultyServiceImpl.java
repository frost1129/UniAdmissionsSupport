/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.AdmissionScore;
import com.linhv.pojo.Faculty;
import com.linhv.pojo.FacultyPost;
import com.linhv.repository.FacultyRepository;
import com.linhv.service.AdmissionScoreService;
import com.linhv.service.FacultyService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class FacultyServiceImpl implements FacultyService{
    
    @Autowired
    private FacultyRepository facultyRepo;
    
    @Autowired
    private AdmissionScoreService scoreService;

    @Override
    public List<Faculty> getAll() {
        return this.facultyRepo.getAll();
    }

    @Override
    public Faculty getFacultyById(int id) {
        return this.facultyRepo.getFacultyById(id);
    }

    @Override
    public boolean add(Faculty f) {
        return this.facultyRepo.add(f);
    }

    @Override
    public boolean update(Faculty f) {
        return this.facultyRepo.update(f);
    }

    @Override
    public boolean delete(int id) {
        List<AdmissionScore> scores = this.scoreService.getAllByFaculty(id);
        for (AdmissionScore s : scores) {
            this.scoreService.delete(s);
        }
        
        return this.facultyRepo.delete(id);
    }

    @Override
    public FacultyPost getFacultyPostById(int id) {
        return this.facultyRepo.getFacultyPostById(id);
    }

    @Override
    public boolean addPost(FacultyPost fp) {
        fp.setUpdatedDate(new Date());
        return this.facultyRepo.addPost(fp);
    }

    @Override
    public boolean updatePost(FacultyPost fp) {
        fp.setUpdatedDate(new Date());
        return this.facultyRepo.updatePost(fp);
    }

}
