/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.QuestionSettings;
import com.linhv.repository.QuestionSettingRepository;
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
public class QuestionSettingRepositoryImpl implements QuestionSettingRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public QuestionSettings getTime() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM QuestionSettings qs WHERE qs.id='HCMOU'");
        return (QuestionSettings) q.getSingleResult();
    }
    
    @Override
    public boolean setTime(QuestionSettings setting) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(setting);        
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
