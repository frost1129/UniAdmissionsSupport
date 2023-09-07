/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.User;
import com.linhv.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author prodi
 */
@Controller
@PropertySource("classpath:configs.properties")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private Environment env;
    
    @GetMapping("/")
    public String login() {
        return "login";
    }
    
    @GetMapping("/admin/users")
    public String allUsers(Model model, @RequestParam Map<String, String> params) {
        if (!params.containsKey("page")) {
            params.put("page", "1"); 
        }
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.userService.countAll();
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        String current = params.get("page");
        if (current != null && !current.isEmpty()) {
            int currentPage = Integer.parseInt(current);
            model.addAttribute("currentPage", currentPage);
        }
        
        model.addAttribute("users", this.userService.getAllUser(params));
        model.addAttribute("user", new User());
        
        return "users";
    }
    
    @GetMapping("/admin/users/create-user")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        
        return "user-detail";
    }
    
    @GetMapping("/admin/users/{id}")
    public String userDetail(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", this.userService.getUserById(id));
        
        return "user-detail";
    }
    
    @PostMapping("/admin/users/add")
    public String create(@ModelAttribute(value = "user") @Valid User user, 
                            BindingResult bs, 
                            Model model) {
        if (!user.getPassword().equals(user.getConfPassword()))
            bs.rejectValue("confPassword", "error.user", "Mật khẩu xác nhận không khớp!");

        if (userService.getUserByEmail(user.getEmail()) != null)
            bs.rejectValue("email", "error.user", "Đã tồn tại tài khoản với email này!");
        
        if (user.getFile().isEmpty())
            bs.rejectValue("file", "error.user", "Vui lòng chọn ảnh đại diện");
        
        if (!bs.hasErrors()) {
            this.userService.addUserByAdmin(user);
            return "redirect:/admin/users";
        }
        model.addAttribute("user", user);
        return "user-detail";
    }
    
    @PostMapping("/admin/users/{id}")
    public String update(@ModelAttribute(value = "user") @Valid User user, BindingResult bs) {
        if (!bs.hasErrors()) {
            if (this.userService.updateUserByAdmin(user))
                return "redirect:/admin/users";
        }
        return "user-detail";
    }
}
