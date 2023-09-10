/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Faqs;
import com.linhv.pojo.QuestionSettings;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    
    @GetMapping("/questions/setting/")
    public ResponseEntity<QuestionSettings> getSetting() {
        return new ResponseEntity<>(this.settingService.getTime(), HttpStatus.OK);
    }
    
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
    
    @GetMapping("/user-questions/by-email/")
    public ResponseEntity<List<UserQuestion>> getQuestionsByEmail(Principal user) {
        List<UserQuestion> list = this.questionService.getQuestionsByUserEmail(user.getName());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/user-questions/advisor/")
    public ResponseEntity<List<UserQuestion>> getQuestionsByAdmission(Principal user) {
        User u = this.userService.getUserByEmail(user.getName());
        List<UserQuestion> list = this.questionService.getQuestionsByAdmissionerId(u.getId());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping(path="/user-questions/{id}/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserQuestion> getQuestionById(@PathVariable(value = "id")int id) {
        UserQuestion uq = this.questionService.getQuestionById(id);
        if (uq == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(uq, HttpStatus.OK);
    }
    
    @PostMapping(path = "/user-question/add/", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserQuestion> addQuestion(@RequestParam Map<String, String> params, Principal askUser) {
        params.put("askUser", askUser.getName());
        UserQuestion uq = this.questionService.addQuestion(params);
        return new ResponseEntity<>(uq, HttpStatus.CREATED);
    }
    
    @PutMapping(path = "/user-question/update/")
    public ResponseEntity<Void> addAnswer(@RequestBody UserQuestion uq, Principal ansUser) {
        if (this.questionService.getQuestionById(uq.getId()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        uq.setAnswerUserId(this.userService.getUserByEmail(ansUser.getName()));
        if (this.questionService.updateQuestion(uq))
            return new ResponseEntity<>(HttpStatus.OK);
        
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
