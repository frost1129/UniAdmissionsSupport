/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Faculty;
import com.linhv.repository.FacultyRepository;
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
public class FacultyRepositoryImpl implements FacultyRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Faculty> getAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Faculty");
        
        return q.getResultList();
    }

    @Override
    public Faculty getFacultyById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("FROM Faculty f WHERE f.id=:id");
            q.setParameter("id", id);
            
            return (Faculty) q.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(Faculty f) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(f);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Faculty f) {
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
    public boolean delete(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(this.getFacultyById(id));
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
