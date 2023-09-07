/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.User;
import com.linhv.repository.UserRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource("classpath:configs.properties")
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    private Environment env;

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
    public List<User> getAllUser(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User");
        
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                q.setMaxResults(pageSize);
                q.setFirstResult((p - 1) * pageSize);
            }
        }
        
        return q.getResultList();
    }

    @Override
    public Long countAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM User");
        
        return Long.valueOf(q.getSingleResult().toString());
    }
}
