/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.slugify.Slugify;
import com.linhv.pojo.Post;
import com.linhv.pojo.User;
import com.linhv.repository.PostRepository;
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
        post.setUserId(new User(3));
        
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean detelePost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Post> getAllPostByAdmission(int id) {
        return this.postRepo.getAllPostByAdmission(id);
    }

    @Override
    public List<Post> get5PostByAdmission(int id) {
        return this.postRepo.get5PostByAdmission(id);
    }
}
