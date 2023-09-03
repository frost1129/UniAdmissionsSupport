/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Post;
import com.linhv.service.PostService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author prodi
 */
@Controller
@RequestMapping("/admin")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/posts")
    public String allPosts(Model model, @RequestParam Map<String, String> params) {
        params.put("postType", "post");
        
        model.addAttribute("posts", this.postService.getAll(params));
        
        return "posts";
    }
    
    @GetMapping("/livestreams")
    public String allLivestreams(Model model, @RequestParam Map<String, String> params) {
        params.put("postType", "livestream");
        
        model.addAttribute("liveposts", this.postService.getAll(params));
        
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
    public String create(@ModelAttribute(value = "post") Post p, BindingResult bs) {
        if (!bs.hasErrors()) {
            if (this.postService.addPost(p) == true)
                return "redirect:/";
        }
        return "createPost";
    }
}
