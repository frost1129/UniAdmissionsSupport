/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Branch;
import com.linhv.repository.BranchRepository;
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
public class BranchRepositoryImpl implements BranchRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Branch add(Branch branch) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(branch);
        
        return branch;
    }

    @Override
    public Branch update(Branch branch) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(branch);
        
        return branch;
    }

    @Override
    public boolean delete(Branch branch) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(branch);
        
        return true;
    }

    @Override
    public Branch getBranchById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from Branch where id=:id");
        q.setParameter("id", id);
        
        return (Branch) q.getSingleResult();
    }

    @Override
    public List<Branch> getAllBranches() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from Branch");
        
        return q.getResultList();
    }

}
