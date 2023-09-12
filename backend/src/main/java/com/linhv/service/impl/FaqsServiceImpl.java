/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.Faqs;
import com.linhv.repository.FaqsRepository;
import com.linhv.service.FaqsService;
import com.linhv.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class FaqsServiceImpl implements FaqsService{
    
    @Autowired
    private FaqsRepository faqsRepo;
    
    @Autowired
    private UserService userService;

    @Override
    public Faqs getFaqsById(int id) {
        return this.faqsRepo.getFaqsById(id);
    }

    @Override
    public List<Faqs> getAll(Map<String, String> params) {
        return this.faqsRepo.getAll(params);
    }

    @Override
    public List<Faqs> getTop5Recent() {
        return this.faqsRepo.getTop5Recent();
    }

    @Override
    public Faqs add(Faqs f, String emai) {
        f.setUserId(this.userService.getUserByEmail(emai));
        f.setUpdatedDate(new Date());
        
        return this.faqsRepo.add(f);
    }

    @Override
    public boolean update(Faqs f) {
        return this.faqsRepo.update(f);
    }

    @Override
    public boolean delete(Faqs f) {
        return this.faqsRepo.delete(f);
    }

    @Override
    public Long countAll() {
        return this.faqsRepo.countAll();
    }

    @Override
    public Long countAllByParams(Map<String, String> params) {
        return this.faqsRepo.countAllByParams(params);
    }

    @Override
    public List<Faqs> getAllByUserId(int id) {
        return this.faqsRepo.getAllByUserId(id);
    }

}
