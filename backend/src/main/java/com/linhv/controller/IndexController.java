/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.service.AdmissionTypeService;
import com.linhv.service.BannerService;
import com.linhv.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author prodi
 */
@Controller
@ControllerAdvice
public class IndexController {
    
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AdmissionTypeService admissionTypeService;
    @Autowired
    private PostService postService;
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("admissionTypes", this.admissionTypeService.getAdmissionType());
    }
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("banners", this.bannerService.getBanners());
        return "index";
    }
}
