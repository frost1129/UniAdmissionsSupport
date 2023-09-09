/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Post;
import com.linhv.pojo.Topic;
import com.linhv.service.PostService;
import com.linhv.service.TopicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prodi
 */
@RestController
@RequestMapping("/api")
public class ApiPostController {
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/posts/top-five/{admissionId}/")
    ResponseEntity<List<Post>> get5PostByAdmission(@PathVariable(value = "admissionId") String id) {    
        return new ResponseEntity<>(this.postService.get5PostByAdmission(Integer.parseInt(id)), HttpStatus.OK);
    }
    
    @GetMapping("/topics/")
    ResponseEntity<List<Topic>> getTopics() {
        return new ResponseEntity<>(this.topicService.getTopics(), HttpStatus.OK);
    }
    
    @GetMapping("/posts/{postId}")
    ResponseEntity<Post> getPostById(@PathVariable(value = "postId") String id) {
        return new ResponseEntity<>(this.postService.getPostById(id), HttpStatus.OK);
    }
}