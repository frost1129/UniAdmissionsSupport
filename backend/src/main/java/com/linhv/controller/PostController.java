/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Post;
import com.linhv.service.PostService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author prodi
 */
@Controller
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/posts")
    public String allPosts(Model model) {
        model.addAttribute("posts", this.postService.getAllPost());
        
        return "posts";
    }
    
    @GetMapping("/livestreams")
    public String allLivestreams(Model model) {
        model.addAttribute("liveposts", this.postService.getAllLivestream());
        
        return "livestreams";
    }
    
    @GetMapping("/create-post")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "createPost";
    }
    
    @GetMapping("/create-post/{id}")
    public String postDetail(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("post", this.postService.getPostById(id));
        return "createPost";
    }
    
    @PostMapping("/create-post")
    public String create(
            @ModelAttribute(value = "post") Post p,
            BindingResult bs) {
        if (!bs.hasErrors()) {
            if (this.postService.addPost(p) == true)
                return "redirect:/";
        }
        return "createPost";
    }
}
