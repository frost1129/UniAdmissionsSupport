/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.Post;
import com.linhv.repository.PostRepository;
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
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:configs.properties")
public class PostRepositoryImpl implements PostRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Environment env;

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
        Query q = s.createQuery("FROM Post p WHERE p.admissionType.id=:id ORDER BY p.updatedDate");
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

    @Override
    public List<Post> getAllPost() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Post p WHERE p.postType=:type ORDER BY p.updatedDate");
        q.setParameter("type", "post");
        
        return q.getResultList();
    }

    @Override
    public List<Post> getAllLivestream() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Post p WHERE p.postType=:type ORDER BY p.updatedDate");
        q.setParameter("type", "livestream");
        
        return q.getResultList();
    }

    @Override
    public List<Post> getAll(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Post> q = b.createQuery(Post.class);
        Root root = q.from(Post.class);
        q.select(root);
        
         if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("title"), String.format("%%%s%%", kw)));
            }
            
            String admissionType = params.get("admissionType");
            if (admissionType != null && !admissionType.isEmpty()) {
                predicates.add(b.equal(root.get("admissionType"), Integer.valueOf(admissionType)));
            }
            
            String postType = params.get("postType");
            if (postType != null && !postType.isEmpty()) {
                predicates.add(b.equal(root.get("postType"), postType));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        
        q.orderBy(b.asc(root.get("updatedDate")));
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
    public List<String> getAllTitle() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT p.title FROM Post p");
        return q.getResultList();
    }

}
