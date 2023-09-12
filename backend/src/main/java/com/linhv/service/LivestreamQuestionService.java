/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.LivestreamQuesion;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prodi
 */
public interface LivestreamQuestionService {
    List<LivestreamQuesion> getAllByPostId(String id);
    LivestreamQuesion getById(String id);
    LivestreamQuesion add(Map<String, String> params);
    boolean update(Map<String, String> params);
    boolean delete(LivestreamQuesion liveQues);
    List<LivestreamQuesion> getAllByUserId(int id);
}
