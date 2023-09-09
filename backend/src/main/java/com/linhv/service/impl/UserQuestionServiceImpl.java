/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.AdmissionType;
import com.linhv.pojo.UserQuestion;
import com.linhv.repository.UserQuestionRepository;
import com.linhv.service.AdmissionTypeService;
import com.linhv.service.UserQuestionService;
import com.linhv.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class UserQuestionServiceImpl implements UserQuestionService{
    
    @Autowired
    private UserQuestionRepository questionRepo;
    
    @Autowired
    private UserService userService;

    @Override
    public List<UserQuestion> getAllQuestions(Map<String, String> params) {
        return this.questionRepo.getAllQuestions(params);
    }

    @Override
    public UserQuestion getQuestionById(int id) {
        return this.questionRepo.getQuestionById(id);
    }

    @Override
    public List<UserQuestion> getQuestionsByUserEmail(String email) {
        return this.questionRepo.getQuestionsByUserEmail(email);
    }

    @Override
    public List<UserQuestion> getQuestionsByAdmissionerId(int id) {
        return this.questionRepo.getQuestionsByAdmissionerId(id);
    }

    @Override
    public List<UserQuestion> getUnansweredQuestions() {
        return this.questionRepo.getUnansweredQuestions();
    }

    @Override
    public List<UserQuestion> getTimedQuestions() {
        return this.questionRepo.getTimedQuestions();
    }

    @Override
    public boolean updateQuestion(Map<String, String> params) {
        UserQuestion ques = this.getQuestionById(Integer.parseInt(params.get("id")));
        ques.setAnswer(params.get("answer"));
        ques.setAnswerUserId(this.userService.getUserByEmail(params.get("answerUser")));
        
        return this.questionRepo.updateQuestion(ques);
    }

    @Override
    public UserQuestion addQuestion(Map<String, String> params) {
        UserQuestion ques = new UserQuestion();
        
        ques.setContent(params.get("content"));
        ques.setAskUserEmail(params.get("askUser"));
        ques.setAdmissionType(new AdmissionType(Integer.valueOf(params.get("admissionType"))));
        ques.setSubmitTime(new Date());
        
        return this.questionRepo.addQuestion(ques);
    }

    @Override
    public boolean deleteQuestion(UserQuestion question) {
        return this.questionRepo.deleteQuestion(question);
    }

    @Override
    public Long countQues() {
        return this.questionRepo.countQues();
    }

    @Override
    public Long countQuesByUserEmail(String email) {
        return this.questionRepo.countQuesByUserEmail(email);
    }

    @Override
    public Long countQuesByAdmissionType(int id) {
        return this.questionRepo.countQuesByAdmissionType(id);
    }
}
