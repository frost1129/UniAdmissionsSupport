/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Faqs;
import com.linhv.repository.FaqsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author prodi
 */
@Repository
@Transactional
public class FaqsRepositoryImpl implements FaqsRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Environment env;

    @Override
    public Faqs getFaqsById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            return s.get(Faqs.class, id);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Faqs> getAll(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Faqs> q = b.createQuery(Faqs.class);
        Root root = q.from(Faqs.class);
        q.select(root);
        
         if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("title"), String.format("%%%s%%", kw)));
            }
            
            q.where(predicates.toArray(Predicate[]::new));
        }
   
        q.orderBy(b.desc(root.get("updatedDate")));
        Query query = session.createQuery(q);
        
        if (params != null) {
            String page = params.get("page");
            int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

            if (page != null) {
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }
        
        return query.getResultList();
    }

    @Override
    public List<Faqs> getTop5Recent() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Faqs f ORDER BY f.updatedDate DESC");
        q.setMaxResults(5);
        
        return q.getResultList();
    }

    @Override
    public Faqs add(Faqs f) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(f);
            return f;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Faqs f) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(f);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Faqs f) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(f);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Long countAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Faqs");
        
        return Long.valueOf(q.getSingleResult().toString());
    }

}
