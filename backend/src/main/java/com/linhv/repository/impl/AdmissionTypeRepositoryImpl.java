/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.AdmissionType;
import com.linhv.repository.AdmissionTypeRepository;
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
public class AdmissionTypeRepositoryImpl implements AdmissionTypeRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public AdmissionType newType(AdmissionType admissionType) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(admissionType);
        
        return admissionType;
    }

    @Override
    public AdmissionType updateType(AdmissionType admissionType) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(admissionType);
        
        return admissionType;
    }

    @Override
    public boolean deleteType(AdmissionType admissionType) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(admissionType);
        
        return true;
    }

    @Override
    public AdmissionType findAdmissionTypeById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from AdmissionType where id=:id");
        q.setParameter("id", id);
        
        return (AdmissionType) q.getSingleResult();
    }

    @Override
    public List<AdmissionType> getAllAdmissionType() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from AdmissionType");
        
        return q.getResultList();
    }

}
