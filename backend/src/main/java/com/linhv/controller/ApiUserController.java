/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.components.JwtService;
import com.linhv.pojo.User;
import com.linhv.service.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author prodi
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/login/")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getEmail(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getEmail());
            
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> details(Principal user) {
        User u = this.userService.getUserByEmail(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    
    @PostMapping(path = "/users/", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        User user = this.userService.addUser(params, avatar);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    @PutMapping("/users/{email}/")
    public ResponseEntity<Void> updateUser(@RequestParam Map<String, String> params) {
        this.userService.updateUser(params);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
