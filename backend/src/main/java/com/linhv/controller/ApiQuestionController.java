/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Faqs;
import com.linhv.pojo.User;
import com.linhv.pojo.UserQuestion;
import com.linhv.service.FaqsService;
import com.linhv.service.QuestionSettingService;
import com.linhv.service.UserQuestionService;
import com.linhv.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prodi
 */
@RestController
@RequestMapping("/api")
public class ApiQuestionController {
    
    @Autowired
    private FaqsService faqService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private QuestionSettingService settingService;
    
    @Autowired
    private UserQuestionService questionService;
    
    @GetMapping("/faqs/top-5/")
    public ResponseEntity<List<Faqs>> get5Faqs() {
        return new ResponseEntity<>(this.faqService.getTop5Recent(), HttpStatus.OK);
    }
    
    @GetMapping("/faqs/")
    public ResponseEntity<List<Faqs>> getAllFaqs(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.faqService.getAll(params), HttpStatus.OK);
    }
    
    @GetMapping("/user-questions/unanswer/")
    public ResponseEntity<List<UserQuestion>> getUnansweredQuestions() {
        List<UserQuestion> list = this.questionService.getUnansweredQuestions();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/user-questions/{email}/")
    public ResponseEntity<List<UserQuestion>> getQuestionsByEmail(@PathVariable(value = "email") String email) {
        List<UserQuestion> list = this.questionService.getQuestionsByUserEmail(email);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/user-questions/advisor/{admissionId}/")
    public ResponseEntity<List<UserQuestion>> getQuestionsByAdmission(@PathVariable(value = "admissionId") int id) {
        List<UserQuestion> list = this.questionService.getQuestionsByAdmissionerId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
