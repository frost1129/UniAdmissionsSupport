/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Banner;
import com.linhv.pojo.Branch;
import com.linhv.pojo.Topic;
import com.linhv.pojo.UniMainInfo;
import com.linhv.service.BannerService;
import com.linhv.service.BranchService;
import com.linhv.service.TopicService;
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
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private TopicService topicService;

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
        return new ResponseEntity<>(this.branchService.getBranches(), HttpStatus.OK);
    }
    
    @GetMapping("/banners/")
    public ResponseEntity<List<Banner>> getBanners() {
        return new ResponseEntity<>(this.bannerService.getBanners(), HttpStatus.OK);
    }
    
    @GetMapping("/topics/")
    public ResponseEntity<List<Topic>> getTopics() {
        return new ResponseEntity<>(this.topicService.getTopics(), HttpStatus.OK);
    }
}
