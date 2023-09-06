/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.AdmissionScore;
import com.linhv.repository.AdmissionScoreRepository;
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
public class AdmissionScoreRepositoryImpl implements AdmissionScoreRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Integer> getAllAdmissionYear() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT a.id FROM AdmissionScore a");
        return q.getResultList();
    }

    @Override
    public List<AdmissionScore> getAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM AdmissionScore");
        return q.getResultList();
    }
    
    @Override
    public List<AdmissionScore> getAllByFaculty(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM AdmissionScore a WHERE a.facultyId.id=:id ORDER BY a.year DESC");
        q.setParameter("id", id);
        
        
        return q.getResultList();
    }

    @Override
    public AdmissionScore getFacultyScoreByYear(int fId, int year) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("FROM AdmissionScore a WHERE a.year=:year AND a.facultyId.id=:fId");
            q.setParameter("year", year);
            q.setParameter("fId", fId);
            
            return (AdmissionScore) q.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(AdmissionScore as) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(as);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(AdmissionScore as) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(as);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(AdmissionScore as) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(as);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isFacultyYearScoreExist(int fId, int year) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("SELECT COUNT(a) > 0 "
                                + "FROM AdmissionScore a "
                                + "WHERE a.year=:year AND a.facultyId.id=:fId");
            q.setParameter("year", year);
            q.setParameter("fId", fId);
            
            return (boolean) q.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
