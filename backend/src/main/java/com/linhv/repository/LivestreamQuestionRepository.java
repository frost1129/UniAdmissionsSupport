/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.LivestreamQuesion;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface LivestreamQuestionRepository {
    List<LivestreamQuesion> getAllByPostId(String id);
    LivestreamQuesion getById(String id);
    LivestreamQuesion add(LivestreamQuesion liveQues);
    boolean update(LivestreamQuesion liveQues);
    boolean delete(LivestreamQuesion liveQues);
}
