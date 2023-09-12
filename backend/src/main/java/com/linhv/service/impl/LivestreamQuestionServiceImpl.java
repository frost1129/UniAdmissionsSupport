/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.LivestreamQuesion;
import com.linhv.pojo.Post;
import com.linhv.pojo.User;
import com.linhv.repository.LivestreamQuestionRepository;
import com.linhv.service.LivestreamQuestionService;
import com.linhv.service.PostService;
import com.linhv.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class LivestreamQuestionServiceImpl implements LivestreamQuestionService{
    
    @Autowired
    private LivestreamQuestionRepository quesRepo;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;

    @Override
    public List<LivestreamQuesion> getAllByPostId(String id) {
        return this.quesRepo.getAllByPostId(id);
    }

    @Override
    public LivestreamQuesion getById(String id) {
        return this.quesRepo.getById(id);
    }

    @Override
    public LivestreamQuesion add(Map<String, String> params) {
        LivestreamQuesion liveQues = new LivestreamQuesion();
        
        String quesId;
        do {
            quesId = UUID.randomUUID().toString();
        } while (this.getById(quesId) != null);
        
        liveQues.setId(quesId);
        liveQues.setContent(params.get("content"));
                
        Post p = postService.getPostById(params.get("postId"));
        liveQues.setLivestreamPostId(p);
        liveQues.setAdmissionType(p.getAdmissionType());
        
        User u = userService.getUserByEmail(params.get("userEmail"));
        liveQues.setUserId(u);
        
        return this.quesRepo.add(liveQues);
    }

    @Override
    public boolean update(Map<String, String> params) {
        LivestreamQuesion liveQues = this.getById(params.get("id"));
        if (liveQues == null)
            return false;
        
        liveQues.setContent(params.get("content"));
        
        return this.quesRepo.update(liveQues);
    }

    @Override
    public boolean delete(LivestreamQuesion liveQues) {
        return this.delete(liveQues);
    }

    @Override
    public List<LivestreamQuesion> getAllByUserId(int id) {
        return this.quesRepo.getAllByUserId(id);
    }

}
