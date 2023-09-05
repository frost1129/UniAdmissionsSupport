/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.QuestionSettings;
import com.linhv.service.QuestionSettingService;
import com.linhv.service.UserQuestionService;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String updateTime(
            @RequestParam("time1") String time1,
            @RequestParam("time2") String time2) {
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date startTime = timeFormat.parse(time1);
            Date endTime = timeFormat.parse(time2);

            QuestionSettings qs = this.settingService.getTime();
            qs.setFromTime(startTime);
            qs.setToTime(endTime);
            
            this.settingService.setTime(qs);
            
            return "redirect:/admin/questions";
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
            return "redirect:/admin/questions"; 
        }
    }
}
