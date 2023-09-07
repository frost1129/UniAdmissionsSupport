/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.UserQuestion;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prodi
 */
public interface UserQuestionService {
    List<UserQuestion> getAllQuestions(Map<String, String> params);
    UserQuestion getQuestionById(int id);
    List<UserQuestion> getQuestionsByUserEmail(String email);
    List<UserQuestion> getQuestionsByAdmissionerId(int id);
    List<UserQuestion> getUnansweredQuestions();
    List<UserQuestion> getTimedQuestions();
    boolean updateQuestion(UserQuestion question);
    UserQuestion addQuestion(UserQuestion question);
    boolean deleteQuestion(UserQuestion question);
    Long countQues();
    Long countQuesByUserEmail(String email);
    Long countQuesByAdmissionType(int id);
}
