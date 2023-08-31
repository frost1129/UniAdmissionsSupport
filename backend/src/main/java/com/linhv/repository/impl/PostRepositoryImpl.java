/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Post;
import com.linhv.repository.PostRepository;
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
public class PostRepositoryImpl implements PostRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Post getPostById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            return s.get(Post.class, id);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addPost(Post post) {
        Session s = this.factory.getObject().getCurrentSession();
        try {        
            s.save(post);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePost(Post post) {
        Session s = this.factory.getObject().getCurrentSession();
        try {        
            s.update(post);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean detelePost(Post post) {
        Session s = this.factory.getObject().getCurrentSession();
        try {        
            s.delete(post);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Post> getAllPostByAdmission(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Post p WHERE p.admissionType.id=:id");
        q.setParameter("id", id);
        
        return q.getResultList();
    }

    @Override
    public List<Post> get5PostByAdmission(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Post p WHERE p.admissionType.id=:id ORDER BY p.updatedDate DESC");
        q.setParameter("id", id);
        q.setMaxResults(5);
        
        return q.getResultList();
    }

}
