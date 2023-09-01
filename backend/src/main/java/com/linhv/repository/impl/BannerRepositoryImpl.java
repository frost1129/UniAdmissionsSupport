/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Banner;
import com.linhv.repository.BannerRepository;
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
public class BannerRepositoryImpl implements BannerRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Banner add(Banner banner) {
        Session s = this.factory.getObject().getCurrentSession();    
        s.save(banner);
        
        return banner;
    }

    @Override
    public boolean update(Banner banner) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(banner);
        
        return true;
    }

    @Override
    public boolean delete(Banner banner) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(banner);
        
        return true;
    }

    @Override
    public Banner getBannerById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from Banner where id=:id");
        q.setParameter("id", id);
        
        return (Banner) q.getSingleResult();
    }

    @Override
    public List<Banner> getAllBanners() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from Banner");
        
        return q.getResultList();
    }
    
    
}
