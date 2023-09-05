/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.QuestionSettings;
import com.linhv.pojo.UserQuestion;
import com.linhv.repository.UserQuestionRepository;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
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
            Query q = s.createQuery("FROM UserQuestion");
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
            Query q = s.createQuery("FROM UserQuestion WHERE id=:id");
            q.setParameter("id", id);
            return (UserQuestion) q.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserQuestion> getQuestionsByUserEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("FROM UserQuestion uq WHERE uq.askUserEmail=:email");
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
            Query q = s.createQuery("FROM UserQuestion uq WHERE uq.answerUserId=:id");
            q.setParameter("id", id);
            return q.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserQuestion> getUnansweredQuestions() {
        Session s = this.factory.getObject().getCurrentSession();
        
        try {
            Query q = s.createQuery("FROM UserQuestion uq WHERE uq.answeruser IS NULL");
            return q.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserQuestion> getTimedQuestions() {
        Session s = this.factory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<UserQuestion> q = builder.createQuery(UserQuestion.class);
        Root<UserQuestion> userQuestionRoot = q.from(UserQuestion.class);

        Subquery<Time> fromTimeSubquery = q.subquery(Time.class);
        Root<QuestionSettings> settingsRoot = fromTimeSubquery.from(QuestionSettings.class);
        fromTimeSubquery.select(settingsRoot.get("fromTime")).where(builder.equal(settingsRoot.get("id"), "HCMOU"));

        Subquery<Time> toTimeSubquery = q.subquery(Time.class);
        toTimeSubquery.select(settingsRoot.get("toTime")).where(builder.equal(settingsRoot.get("id"), "HCMOU"));

        Expression<Time> sendtimeTime = builder.function("TIME", Time.class, userQuestionRoot.get("sendtime"));

        Predicate notBetween = builder.or(
            builder.lessThan(sendtimeTime, fromTimeSubquery),
            builder.greaterThan(sendtimeTime, toTimeSubquery)
        );

        q.where(notBetween);

        Query query = s.createQuery(q);
        return query.getResultList();
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
