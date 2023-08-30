/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.service.BannerService;
import com.linhv.service.UniMainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author prodi
 */
@Controller
public class IndexController {
    
    @Autowired
    private UniMainInfoService uniMainService;
    @Autowired
    private BannerService bannerService;
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("banners", this.bannerService.getBanners());
        
        return "index";
    }
}
