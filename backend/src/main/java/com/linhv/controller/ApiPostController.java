/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Post;
import com.linhv.pojo.PostComment;
import com.linhv.pojo.Topic;
import com.linhv.service.LivestreamQuestionService;
import com.linhv.service.PostCommentService;
import com.linhv.service.PostService;
import com.linhv.service.TopicService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prodi
 */
@RestController
@RequestMapping("/api")
@PropertySource("classpath:configs.properties")
public class ApiPostController {
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private PostCommentService commentService;
    
    @Autowired
    private LivestreamQuestionService questionService;
    
    @Autowired
    private Environment env;
    
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
    
    @GetMapping("/posts/")
    ResponseEntity<List<Post>> getPosts(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.postService.getAll(params), HttpStatus.OK);
    }
    
    @GetMapping("/posts/counter/")
    ResponseEntity<Integer> getPostsCount(@RequestParam Map<String, String> params) {
        Long count = this.postService.countAll(params);
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int counter = (int) Math.ceil(count*1.0/pageSize);
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }
    
    @PostMapping(path = "/posts/{postId}/add-comment/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> addComment(@PathVariable(value = "postId") String id, 
                                @RequestParam Map<String, String> params, 
                                Principal user) {
        if (this.postService.getPostById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        params.put("postId", id);
        params.put("userEmail", user.getName());
        
        this.commentService.add(params);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("/posts/{postId}/comments/")
    ResponseEntity<List<PostComment>> getAllComment(@PathVariable(value = "postId") String id) {
        return new ResponseEntity<>(this.commentService.getAllByPostId(id), HttpStatus.OK);
    }
    
    @PostMapping(path = "/posts/{postId}/add-question/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> addQuestion(@PathVariable(value = "postId") String id, 
                                @RequestParam Map<String, String> params, 
                                Principal user) {
        if (this.postService.getPostById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        params.put("postId", id);
        params.put("userEmail", user.getName());
        
        this.questionService.add(params);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
