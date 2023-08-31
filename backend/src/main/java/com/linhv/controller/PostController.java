/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author prodi
 */
@Controller
public class PostController {
    @GetMapping("/create-post")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "createPost";
    }
    
    @PostMapping("/create-post")
    public String create(@ModelAttribute(value = "product") Post p, 
            @RequestParam("content") String content,
            BindingResult bs) {
        if (bs.hasErrors()) {
            
            return "createPost"; // Trả về trang sửa đổi nếu có lỗi
        }
        
        p.setContent(content);
//        this.postService.addPost(p);
        
        return "index";
    }
}
