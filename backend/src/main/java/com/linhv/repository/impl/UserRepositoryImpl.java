/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.User;
import com.linhv.repository.UserRepository;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author prodi
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    LocalSessionFactoryBean factory;
    
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public User getUserByEmail(String email) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("from User where email=:e");
        q.setParameter("e", email);
        
        return (User) q.getSingleResult();
    }

    @Override
    public User addUser(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(user);
        
        return user;
    }

    @Override
    public boolean authUser(String email, String password) {
        return  false;
    }

}
