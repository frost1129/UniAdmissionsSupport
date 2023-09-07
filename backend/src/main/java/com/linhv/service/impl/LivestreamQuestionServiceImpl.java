/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.LivestreamQuesion;
import com.linhv.repository.LivestreamQuestionRepository;
import com.linhv.service.LivestreamQuestionService;
import java.util.List;
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

    @Override
    public List<LivestreamQuesion> getAllByPostId(String id) {
        return this.quesRepo.getAllByPostId(id);
    }

    @Override
    public LivestreamQuesion getById(String id) {
        return this.quesRepo.getById(id);
    }

    @Override
    public LivestreamQuesion add(LivestreamQuesion liveQues) {
        return this.quesRepo.add(liveQues);
    }

    @Override
    public boolean update(LivestreamQuesion liveQues) {
        return this.update(liveQues);
    }

    @Override
    public boolean delete(LivestreamQuesion liveQues) {
        return this.delete(liveQues);
    }

}
