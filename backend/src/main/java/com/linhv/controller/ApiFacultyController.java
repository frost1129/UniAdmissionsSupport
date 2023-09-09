/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.AdmissionScore;
import com.linhv.pojo.Faculty;
import com.linhv.pojo.FacultyPost;
import com.linhv.service.AdmissionScoreService;
import com.linhv.service.FacultyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prodi
 */
@RestController
@RequestMapping("/api")
public class ApiFacultyController {
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private AdmissionScoreService scoreService;
    
    @GetMapping("/faculties/")
    ResponseEntity<List<Faculty>> getAll() {
        return new ResponseEntity<>(this.facultyService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/faculties/{id}/")
    ResponseEntity<Faculty> getFaculty(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.facultyService.getFacultyById(id), HttpStatus.OK);
    }
    
    @GetMapping("/faculty-score/{id}/")
    ResponseEntity<List<AdmissionScore>> getFacultyScore(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.scoreService.get5ByFaculty(id), HttpStatus.OK);
    }
    
    @GetMapping("/faculty-post/{id}/")
    ResponseEntity<FacultyPost> getFacultyPost(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.facultyService.getFacultyPostById(id), HttpStatus.OK);
    }
}
