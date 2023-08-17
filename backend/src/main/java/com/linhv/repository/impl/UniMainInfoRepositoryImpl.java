/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Banner;
import com.linhv.pojo.Branch;
import com.linhv.pojo.UniMainInfo;
import com.linhv.repository.UniMainInfoRepository;
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
public class UniMainInfoRepositoryImpl implements UniMainInfoRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public UniMainInfo getUniInfo(String id) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("from UniMainInfo where id=:id");
        q.setParameter("id", id);
        
        return (UniMainInfo) q.getSingleResult();
    }

    @Override
    public List<Branch> getBranches() {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("from Branch");
        
        return q.getResultList();
    }

    @Override
    public List<Banner> getBanners() {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("from Banner");
        
        return q.getResultList();
    }
    
}
