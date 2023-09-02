/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.UserQuestion;
import com.linhv.repository.UserQuestionRepository;
import com.linhv.service.UserQuestionService;
import java.util.List;
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

    @Override
    public List<UserQuestion> getAllQuestions() {
        return this.questionRepo.getAllQuestions();
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
    public boolean updateQuestion(UserQuestion question) {
        return this.questionRepo.updateQuestion(question);
    }

    @Override
    public UserQuestion addQuestion(UserQuestion question) {
        return this.questionRepo.addQuestion(question);
    }

    @Override
    public boolean deleteQuestion(UserQuestion question) {
        return this.questionRepo.deleteQuestion(question);
    }
}
