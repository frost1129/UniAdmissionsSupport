/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.Faculty;
import com.linhv.repository.FacultyRepository;
import com.linhv.service.FacultyService;
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
        return this.facultyRepo.delete(id);
    }

}
