/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.Post;
import com.linhv.pojo.PostComment;
import com.linhv.pojo.User;
import com.linhv.repository.PostCommentRepository;
import com.linhv.service.PostCommentService;
import com.linhv.service.PostService;
import com.linhv.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;

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
    public PostComment add(Map<String, String> params) {
        PostComment pc = new PostComment();

        String postCommentId;
        do {
            postCommentId = UUID.randomUUID().toString();
        } while (this.getById(postCommentId) != null);
        
        pc.setId(postCommentId);
        pc.setContent(params.get("content"));
        pc.setUpdatedDate(new Date());
        
        Post p = postService.getPostById(params.get("postId"));
        pc.setPostId(p);
        
        User u = userService.getUserByEmail(params.get("userEmail"));
        pc.setUserId(u);
        
        return this.commentRepo.add(pc);
    }

    @Override
    public boolean update(Map<String, String> params) {
        PostComment pc = this.getById(params.get("id"));
        if (pc == null)
            return false;
        
        pc.setContent(params.get("content"));
        pc.setUpdatedDate(new Date());
        
        return this.commentRepo.update(pc);
    }

    @Override
    public boolean delete(String id) {
        return this.commentRepo.delete(this.getById(id));
    }

    @Override
    public int countCommentByPostId(String id) {
        return this.commentRepo.countCommentByPostId(id);
    }

    @Override
    public List<PostComment> getAllByUserId(int id) {
        return this.commentRepo.getAllByUserId(id);
    }

}
