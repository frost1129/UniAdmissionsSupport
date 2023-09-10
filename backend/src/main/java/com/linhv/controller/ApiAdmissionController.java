/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.AdmissionType;
import com.linhv.service.AdmissionTypeService;
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
public class ApiAdmissionController {

    @Autowired
    private AdmissionTypeService typeService;
    
    @GetMapping("/admissions/")
    public ResponseEntity<List<AdmissionType>> getAdmissionTypes() {
        return new ResponseEntity<>(this.typeService.getAdmissionType(), HttpStatus.OK);
    }
    
    @GetMapping("/admissions/{id}/")
    public ResponseEntity<AdmissionType> getAdmissionById(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.typeService.getById(id), HttpStatus.OK);
    }
}
