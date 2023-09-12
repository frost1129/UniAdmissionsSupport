/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.repository.impl;

import com.linhv.pojo.PostComment;
import com.linhv.repository.PostCommentRepository;
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
public class PostCommentRepositoryImpl implements PostCommentRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<PostComment> getAllByPostId(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM PostComment cmt "
                                    + "WHERE cmt.postId.id=:id ORDER BY cmt.updatedDate");
        q.setParameter("id", id);
        
        return q.getResultList();
    }

    @Override
    public List<PostComment> getSubsByCommentId(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM PostComment cmt "
                                    + "WHERE cmt.commentId.id=:id ORDER BY cmt.updatedDate");
        q.setParameter("id", id);
        
        return q.getResultList();
    }

    @Override
    public PostComment getById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            return s.get(PostComment.class, id);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PostComment add(PostComment comment) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(comment);
            return comment;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(PostComment comment) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(comment);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(PostComment comment) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(comment);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countCommentByPostId(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM PostComment cmt "
                                    + "WHERE cmt.postId.id=:id");
        q.setParameter("id", id);
        
        return (int) q.getSingleResult();
    }

    @Override
    public List<PostComment> getAllByUserId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM PostComment cmt WHERE cmt.userId.id=:id");
        q.setParameter("id", id);
        
        return q.getResultList();
    }

}
