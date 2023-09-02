/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.QuestionSettings;
import com.linhv.pojo.UserQuestion;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface UserQuestionRepository {
    List<UserQuestion> getAllQuestions();
    UserQuestion getQuestionById(int id);
    List<UserQuestion> getQuestionsByUserEmail(String email);
    List<UserQuestion> getQuestionsByAdmissionerId(int id);
    List<UserQuestion> getUnansweredQuestions();
    List<UserQuestion> getTimedQuestions();
    boolean updateQuestion(UserQuestion question);
    UserQuestion addQuestion(UserQuestion question);
    boolean deleteQuestion(UserQuestion question);
}
