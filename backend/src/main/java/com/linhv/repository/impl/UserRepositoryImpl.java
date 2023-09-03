/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.User;
import com.linhv.repository.UserRepository;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
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
        try {
            Query q = session.createQuery("FROM User WHERE email=:e");
            q.setParameter("e", email);
            
            return (User) q.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public User getUserById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query q = session.createQuery("FROM User WHERE id=:id");
            q.setParameter("id", id);
            
            return (User) q.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(user);
        
        return user;
    }

    @Override
    public boolean authUser(String email, String password) {
        User user = getUserByEmail(email);
        return this.encoder.matches(password, user.getPassword());
    }

    @Override
    public boolean updateUser(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.merge(user);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    @Override
    public List<User> getAllUser() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User");
        return q.getResultList();
    }

}
