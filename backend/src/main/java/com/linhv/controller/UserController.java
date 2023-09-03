/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.User;
import com.linhv.service.UserService;
import javax.validation.Valid;
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
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String login() {
        return "login";
    }
    
    @GetMapping("/admin/users")
    public String allUsers(Model model) {
        model.addAttribute("users", this.userService.getAllUser());
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
    public String create(@ModelAttribute(value = "user") @Valid User user, BindingResult bs) {
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
