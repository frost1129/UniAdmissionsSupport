/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.Post;
import com.linhv.pojo.User;
import com.linhv.repository.PostRepository;
import com.linhv.service.PostService;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class PostServiceImpl implements PostService{
    
    private final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss");
    
    @Autowired
    private PostRepository postRepo;

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
    public Post addPost(Post post) {
        post.setId(this.toSlug(post.getTitle()));
        post.setUpdatedDate(new Date());
        post.setUserId(new User(3));
        
        if (post.getPostType().equals("post")) {
            post.setAllowComment(true);
            post.setAllowQuestion(false);
        } else if (post.getPostType().equals("livestream")) {
            post.setAllowComment(false);
            post.setAllowQuestion(true);
        }

        return this.postRepo.addPost(post);
    }
    
    public String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = slug.toLowerCase(Locale.ENGLISH);

        // Kiểm tra xem slug có trùng với các slug hiện tại không
        // Nếu có, thêm ngày tháng năm và giờ vào slug
        // Ví dụ: my-post -> my-post-20230830-150000
        if (isDuplicateSlug(slug)) {
            String timestamp = DATE_FORMAT.format(new Date());
            slug = slug + "-" + timestamp;
        }

        return slug;
    }

    private boolean isDuplicateSlug(String slug) {
//        return this.postService.getPostById(slug) != null; 
    return false;
    }

}
