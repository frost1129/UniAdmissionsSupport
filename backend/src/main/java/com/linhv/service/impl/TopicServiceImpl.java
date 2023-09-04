/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.Topic;
import com.linhv.repository.TopicRepository;
import com.linhv.service.TopicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class TopicServiceImpl implements TopicService {
    
    @Autowired
    private TopicRepository topicRepo;

    @Override
    public List<Topic> getTopics() {
        return this.topicRepo.getAllTopics();
    }

    @Override
    public Topic getTopicById(int id) {
        return this.topicRepo.getTopicById(id);
    }

    @Override
    public Topic addTopic(Topic topic) {
        return this.topicRepo.addTopic(topic);
    }

    @Override
    public boolean deleteTopic(int id) {
        return this.topicRepo.deleteTopic(id);
    }

}
