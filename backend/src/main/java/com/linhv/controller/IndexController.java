/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Banner;
import com.linhv.pojo.Branch;
import com.linhv.pojo.Topic;
import com.linhv.pojo.User;
import com.linhv.service.AdmissionTypeService;
import com.linhv.service.BannerService;
import com.linhv.service.BranchService;
import com.linhv.service.PostService;
import com.linhv.service.TopicService;
import com.linhv.service.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
@ControllerAdvice
@RequestMapping("/admin")
public class IndexController {
    
    @Autowired
    private BannerService bannerService;
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private AdmissionTypeService admissionTypeService;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("admissionTypes", this.admissionTypeService.getAdmissionType());
    }
    
    @ModelAttribute("userInfo")
    public User getUser(Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = this.userService.getUserByEmail(email);
            
            return user;
        }
        return null; // hoặc một giá trị mặc định nếu không có người dùng đăng nhập
    }
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("banner", new Banner());
        model.addAttribute("banners", this.bannerService.getBanners());
        model.addAttribute("branches", this.branchService.getBranches());
        model.addAttribute("topics", this.topicService.getTopics());
        
        return "index";
    }
    
    @PostMapping("/add-banner")
    public String addBanner(@ModelAttribute(value = "banner") Banner b, BindingResult bs) {
        if (!bs.hasErrors()) {
            this.bannerService.add(b);
        }
        return "redirect:/admin/";
    }
    
    @PostMapping("/banners/{id}")
    public String deleteBanner(@PathVariable(value = "id") int id) {
        this.bannerService.delete(this.bannerService.getBannerById(id));
        return "redirect:/admin/";
    }
    
    @GetMapping("/topics/topic-detail")
    public String topicDetail(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("posts", this.postService.getAll(params));
        model.addAttribute("topic", new Topic());
        return "topic-detail";
    }
    
    @PostMapping("/topics/topic-detail")
    public String addTopic(@ModelAttribute(value = "topic") @Valid Topic t, 
                            BindingResult bs, 
                            Model model) {
//        bs.rejectValue("title", "error.topic", t.getTitle());
        
        if (!bs.hasErrors()){
            this.topicService.addTopic(t);
            return "redirect:/admin/";
        }
        Map<String, String> params = new HashMap<>();
        model.addAttribute("posts", this.postService.getAll(params));
        model.addAttribute("topic", new Topic());
        return "topic-detail";
    }
    
    @PostMapping("/topics/{id}")
    public String deleteTopic(@PathVariable(value = "id") int id) {
        this.topicService.deleteTopic(id);
        return "redirect:/admin/";
    }
    
    @GetMapping("/branches/branch-detail")
    public String branchDetail(Model model) {
        model.addAttribute("branch", new Branch());
        return "branch-detail";
    }
    
    @PostMapping("/branches/add-or-update")
    public String addOrUpdateBranch(@ModelAttribute(value = "branch") @Valid Branch b, 
                                    BindingResult bs, 
                                    Model model) {
        if (!bs.hasErrors()) {
            if (b.getId() != null) 
                this.branchService.update(b);
            else 
                this.branchService.add(b);
            return "redirect:/admin/";
        }
        model.addAttribute("branch", b);
        return "branch-detail";
    }
    
    @GetMapping("/branches/{id}")
    public String existBranch(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute(this.branchService.getBranchById(id));
        return "branch-detail";
    }
    
    @PostMapping("/branches/{id}")
    public String deleteBranch(@PathVariable(value = "id") int id) {
        this.branchService.delete(this.branchService.getBranchById(id));
        return "redirect:/admin/";
    }
}
