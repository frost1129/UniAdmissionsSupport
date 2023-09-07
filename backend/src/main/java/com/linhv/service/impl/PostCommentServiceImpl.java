/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.PostComment;
import com.linhv.repository.PostCommentRepository;
import com.linhv.service.PostCommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class PostCommentServiceImpl implements PostCommentService{
    
    @Autowired
    private PostCommentRepository commentRepo;

    @Override
    public List<PostComment> getAllByPostId(String id) {
        return this.commentRepo.getAllByPostId(id);
    }

    @Override
    public List<PostComment> getSubsByCommentId(String id) {
        return this.commentRepo.getSubsByCommentId(id);
    }

    @Override
    public PostComment getById(String id) {
        return this.commentRepo.getById(id);
    }

    @Override
    public PostComment add(PostComment comment) {
        return this.commentRepo.add(comment);
    }

    @Override
    public boolean update(PostComment comment) {
        return this.commentRepo.update(comment);
    }

    @Override
    public boolean delete(PostComment comment) {
        return this.commentRepo.delete(comment);
    }

}
