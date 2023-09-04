/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.Topic;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface TopicService {
    List<Topic> getTopics();
    Topic getTopicById(int id);
    Topic addTopic(Topic topic);
    boolean deleteTopic(int id);
}
