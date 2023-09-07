/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.LivestreamQuesion;
import com.linhv.repository.LivestreamQuestionRepository;
import java.util.List;
import javax.persistence.NoResultException;
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
public class LivestreamQuestionRepositoryImpl implements LivestreamQuestionRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<LivestreamQuesion> getAllByPostId(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM LivestreamQuestion lq "
                                    + "WHERE lq.livestreamPostId.id=:id ");
        q.setParameter("id", id);
        
        return q.getResultList();
    }

    @Override
    public LivestreamQuesion getById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            return s.get(LivestreamQuesion.class, id);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public LivestreamQuesion add(LivestreamQuesion liveQues) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(liveQues);
            return liveQues;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(LivestreamQuesion liveQues) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(liveQues);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(LivestreamQuesion liveQues) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(liveQues);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
