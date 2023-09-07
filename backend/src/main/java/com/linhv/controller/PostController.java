/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Post;
import com.linhv.service.PostService;
import com.linhv.service.UserService;
import java.security.Principal;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private Environment env;
    
    @GetMapping("/posts")
    public String allPosts(Model model, @RequestParam Map<String, String> params) {
        params.put("postType", "post");
        if (!params.containsKey("page")) {
            params.put("page", "1"); 
        }
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.postService.countAll(params);
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        String current = params.get("page");
        String kw = params.get("kw");
        if (current != null && !current.isEmpty()) {
            int currentPage = Integer.parseInt(current);
            model.addAttribute("currentPage", currentPage);
        }
        
        model.addAttribute("posts", this.postService.getAll(params));
        
        return "posts";
    }
    
    @GetMapping("/livestreams")
    public String allLivestreams(Model model, @RequestParam Map<String, String> params) {
        params.put("postType", "livestream");
        if (!params.containsKey("page")) {
            params.put("page", "1"); 
        }
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.postService.countAll(params);
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        String current = params.get("page");
        if (current != null && !current.isEmpty()) {
            int currentPage = Integer.parseInt(current);
            model.addAttribute("currentPage", currentPage);
        }
        
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
    
//    @PostMapping("/create-post")
//    public String create(@ModelAttribute(value = "post") @Valid Post p, 
//                            Principal principal, 
//                            BindingResult bs) {
//        
//        if (!bs.hasErrors()) {
//            if (p.getFile().isEmpty()) {
//                bs.rejectValue("file", "error.post", "Vui lòng chọn ảnh cho bài đăng");
//            }
//            p.setUserId(this.userService.getUserByEmail(principal.getName()));
//            
//            if (this.postService.addPost(p) == true)
//            return "redirect:/admin/";
//        }
//        return "createPost";
//    }
    
//    @PostMapping("/update-post")
//    public String update(@ModelAttribute(value = "post") @Valid Post p, 
//                            Principal principal,
//                            BindingResult bs) {
//        if (!bs.hasErrors()) {
//            p.setUserId(this.userService.getUserByEmail(principal.getName()));
//            
//            if (this.postService.updatePost(p) == true)
//                return "redirect:/admin/";
//        }
//        return "createPost";
//    }
    
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable(value = "id") String id) {
        String type = this.postService.getPostById(id).getPostType();
        this.postService.deletePost(id);
        return String.format("redirect:/admin/%ss", type);
    }
    
    @PostMapping("/add-or-update")
    public String addOrUpdate(@ModelAttribute(value = "post") @Valid Post p, 
                                BindingResult bs, 
                                Principal principal,
                                Model model) {
        if (!bs.hasErrors()) {
            p.setUserId(this.userService.getUserByEmail(principal.getName()));
            if (!p.getId().isBlank())
                this.postService.updatePost(p);
                
            // xử lý lỗi không upload ảnh bài đăng
            else if (p.getFile().isEmpty()) {
                bs.rejectValue("file", "error.post", "Vui lòng chọn ảnh cho bài đăng");
                model.addAttribute("post", p);
                return "createPost";
            }
            else 
                this.postService.addPost(p);
            
            String type = p.getPostType();
            return String.format("redirect:/admin/%ss", type);
        }
        
        model.addAttribute("post", p);
        return "createPost";
    }
}
