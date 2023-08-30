/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Post;
import com.linhv.repository.PostRepository;
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
public class PostRepositoryImpl implements PostRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Post getPostById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from Post where id=:id");
        q.setParameter("id", id);
        
        return (Post) q.getSingleResult();
    }

    @Override
    public Post addPost(Post post) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(post);
        
        return post;
    }

}
