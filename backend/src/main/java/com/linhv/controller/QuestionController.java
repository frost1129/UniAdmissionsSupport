/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.QuestionSettings;
import com.linhv.service.QuestionSettingService;
import com.linhv.service.UserQuestionService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author prodi
 */
@Controller
@RequestMapping("/admin")
public class QuestionController {
    
    @Autowired
    private UserQuestionService questionService;
    
    @Autowired
    private QuestionSettingService settingService;
    
    @GetMapping("/questions")
    public String allQuestions(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("times", this.settingService.getTime());
        
        return "questions";
    }
    
    @PostMapping("/questions/update-time")
    public String updateTime(@ModelAttribute(value = "times") QuestionSettings qs, BindingResult bs) {
        if (!bs.hasErrors()) {
            this.settingService.setTime(qs);
            return "redirect:/admin/questions";
        }
        
        return "redirect:/admin/";
    }
}
