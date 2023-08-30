/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Topic;
import com.linhv.repository.TopicRepository;
import java.util.List;
import javax.persistence.Query;
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
public class TopicRepositoryImpl implements TopicRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Topic> getAllTopics() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from Topic");
        
        return q.getResultList();
    }

}
