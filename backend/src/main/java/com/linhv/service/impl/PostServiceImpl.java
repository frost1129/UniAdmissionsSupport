/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.slugify.Slugify;
import com.linhv.pojo.LivestreamQuesion;
import com.linhv.pojo.Post;
import com.linhv.pojo.PostComment;
import com.linhv.repository.PostRepository;
import com.linhv.service.LivestreamQuestionService;
import com.linhv.service.PostCommentService;
import com.linhv.service.PostService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class PostServiceImpl implements PostService{
    
    @Autowired
    private PostRepository postRepo;
    
    @Autowired
    private PostCommentService commentService;
    
    @Autowired
    private LivestreamQuestionService questionService;
    
    @Autowired
    private Cloudinary cloudinary;
    
    private Slugify slg = Slugify.builder().build();
    
    @Override
    public Post getPostById(String id) {
        Post p = null;
        try {
            p = this.postRepo.getPostById(id);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public boolean addPost(Post post) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String formattedDate = dateFormat.format(new Date());
        
        post.setId(slg.slugify(post.getTitle()) + "-" + formattedDate);
        post.setUpdatedDate(new Date());
        
        if (post.getPostType().equals("post")) {
            post.setAllowComment(true);
            post.setAllowQuestion(false);
        } else if (post.getPostType().equals("livestream")) {
            post.setAllowComment(false);
            post.setAllowQuestion(true);
        }
        
        if (!post.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(post.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                post.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return this.postRepo.addPost(post);
    }

    @Override
    public boolean updatePost(Post post) {
        post.setUpdatedDate(new Date());
        
        if (post.getPostType().equals("post")) {
            post.setAllowComment(true);
            post.setAllowQuestion(false);
        } else if (post.getPostType().equals("livestream")) {
            post.setAllowComment(false);
            post.setAllowQuestion(true);
        }
        
        if (!post.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(post.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                post.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return this.postRepo.updatePost(post);
    }

    @Override
    public boolean deletePost(String id) {
        Post p = this.getPostById(id);
        if (p != null) {
            // xử lý xóa cmt + câu hỏi
            List<PostComment> cmts = this.commentService.getAllByPostId(id);
            for (PostComment cmt : cmts) {
                this.commentService.delete(cmt.getId());
            }
            
            List<LivestreamQuesion> questions = this.questionService.getAllByPostId(id);
            for (LivestreamQuesion ques : questions) {
                this.questionService.delete(ques);
            }
            
            return this.postRepo.detelePost(p);
        }
        return false;
    }

    @Override
    public List<Post> getAllPostByAdmission(int id, Map<String, String> params) {
        return this.postRepo.getAllPostByAdmission(id, params);
    }

    @Override
    public List<Post> get5PostByAdmission(int id) {
        return this.postRepo.get5PostByAdmission(id);
    }

    @Override
    public List<Post> getAllPost(Map<String, String> params) {
        return this.postRepo.getAllPost(params);
    }

    @Override
    public List<Post> getAllLivestream(Map<String, String> params) {
        return this.postRepo.getAllLivestream(params);
    }

    @Override
    public List<Post> getAll(Map<String, String> map) {
        return this.postRepo.getAll(map);
    }

    @Override
    public List<String> getAllTitle() {
        return this.postRepo.getAllTitle();
    }

    @Override
    public Long countAll(Map<String, String> params) {
        return this.postRepo.countAll(params);
    }

    @Override
    public Long countByAdmission(int admissionId) {
        return this.postRepo.countByAdmission(admissionId);
    }

    @Override
    public Long countPost() {
        return this.postRepo.countPost();
    }

    @Override
    public Long countLivestream() {
        return this.postRepo.countLivestream();
    }

    @Override
    public List<Post> getAllByUserId(int id) {
        return this.postRepo.getAllByUserId(id);
    }
}
