/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Banner;
import com.linhv.pojo.Branch;
import com.linhv.pojo.UniMainInfo;
import com.linhv.service.UniMainInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prodi
 */
@RestController
@RequestMapping("/api")
//@CrossOrigin
public class ApiUniInfoController {
    
    @Autowired
    private UniMainInfoService uniMainService;

    @GetMapping("/hello/")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
    
    @GetMapping("/info/")
    public ResponseEntity<UniMainInfo> getUniInfo() {
        return new ResponseEntity<>(this.uniMainService.getUniInfo("HCMOU"), HttpStatus.OK);
    }
    
    @GetMapping("/branches/")
    public ResponseEntity<List<Branch>> getBranches() {
        return new ResponseEntity<>(this.uniMainService.getBranches(), HttpStatus.OK);
    }
    
    @GetMapping("/banners/")
    public ResponseEntity<List<Banner>> getBanners() {
        return new ResponseEntity<>(this.uniMainService.getBanners(), HttpStatus.OK);
    }
}
