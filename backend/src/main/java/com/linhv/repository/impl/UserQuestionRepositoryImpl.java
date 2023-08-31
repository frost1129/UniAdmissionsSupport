/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.UserQuestion;
import com.linhv.repository.UserQuestionRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author prodi
 */
@Repository
@Transactional
public class UserQuestionRepositoryImpl implements UserQuestionRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<UserQuestion> getAllQuestions() {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("FROM userQuestion");
            return q.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public UserQuestion getQuestionById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("FROM userQuestion WHERE id=:id");
            q.setParameter("id", id);
            return (UserQuestion) q.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserQuestion> getQuestionsByUserEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("FROM userQuestion uq WHERE uq.askUserEmail=:email");
            q.setParameter("email", email);
            return q.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserQuestion> getQuestionsByAdmissionerId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("FROM userQuestion uq WHERE uq.answerUserId=:id");
            q.setParameter("id", id);
            return q.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserQuestion> getUnansweredQuestions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UserQuestion> getTimedQuestions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateQuestion(UserQuestion question) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(question);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public UserQuestion addQuestion(UserQuestion question) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(question);
        return question;
    }

    @Override
    public boolean deleteQuestion(UserQuestion question) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
