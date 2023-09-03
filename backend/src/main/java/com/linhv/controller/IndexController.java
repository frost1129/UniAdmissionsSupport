/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Banner;
import com.linhv.pojo.User;
import com.linhv.service.AdmissionTypeService;
import com.linhv.service.BannerService;
import com.linhv.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author prodi
 */
@Controller
@ControllerAdvice
@RequestMapping("/admin")
public class IndexController {
    
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AdmissionTypeService admissionTypeService;
    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("admissionTypes", this.admissionTypeService.getAdmissionType());
    }
    
    @ModelAttribute("userInfo")
    public User getUser(Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = this.userService.getUserByEmail(email);
            
            return user;
        }
        return null; // hoặc một giá trị mặc định nếu không có người dùng đăng nhập
    }
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("banner", new Banner());
        model.addAttribute("banners", this.bannerService.getBanners());
        return "index";
    }
    
    @PostMapping("/add-banner")
    public String addBanner(@ModelAttribute(value = "banner") Banner b, BindingResult bs) {
        if (!bs.hasErrors()) {
            this.bannerService.add(b);
        }
        return "redirect:/admin/";
    }
    
    @PostMapping("/banners/{id}")
    public String deleteBanner(@PathVariable(value = "id") int id) {
        this.bannerService.delete(this.bannerService.getBannerById(id));
        return "redirect:/admin/";
    }
}
