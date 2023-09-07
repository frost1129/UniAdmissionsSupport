/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Faqs;
import com.linhv.pojo.QuestionSettings;
import com.linhv.service.FaqsService;
import com.linhv.service.QuestionSettingService;
import com.linhv.service.UserQuestionService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author prodi
 */
@Controller
@RequestMapping("/admin")
@PropertySource("classpath:configs.properties")
public class QuestionController {
    
    @Autowired
    private UserQuestionService questionService;
    
    @Autowired
    private QuestionSettingService settingService;
    
    @Autowired
    private FaqsService faqService;
    
    @Autowired
    private Environment env;
    
    @GetMapping("/questions")
    public String allFAQs(Model model, @RequestParam Map<String, String> params) {
        if (!params.containsKey("page")) {
            params.put("page", "1"); 
        }
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.faqService.countAll();
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        String current = params.get("page");
        if (current != null && !current.isEmpty()) {
            int currentPage = Integer.parseInt(current);
            model.addAttribute("currentPage", currentPage);
        }
        
        model.addAttribute("times", this.settingService.getTime());
        model.addAttribute("faq", new Faqs());
        model.addAttribute("faqs", this.faqService.getAll(params));
        
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
    
    @PostMapping("/questions/add-faq")
    public String addFaq(@ModelAttribute(value = "faq") Faqs f, Principal principal) {
        this.faqService.add(f, principal.getName());
        return "redirect:/admin/questions";
    }
    
    @PostMapping("/questions/faq/{id}")
    public String deleteFaq(@PathVariable(value = "id") int id) {
        this.faqService.delete(this.faqService.getFaqsById(id));
        return "redirect:/admin/questions";
    }
    
    @GetMapping("/questions/user-questions")
    public String allUserQuestions(Model model, 
                                    @RequestParam Map<String, String> params) {
        // Đặt giá trị mặc định cho "page" là 1 nếu không tồn tại
        if (!params.containsKey("page")) {
            params.put("page", "1"); 
        }
        
        // PHÂN TRANG
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.questionService.countQues();
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        String current = params.get("page");
        if (current != null && !current.isEmpty()) {
            int currentPage = Integer.parseInt(current);
            model.addAttribute("currentPage", currentPage);
        }
        
        model.addAttribute("questions", this.questionService.getAllQuestions(params));
        
        return "user-questions";
    }
}
