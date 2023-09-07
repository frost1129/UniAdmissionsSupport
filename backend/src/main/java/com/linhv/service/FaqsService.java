/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.Faqs;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prodi
 */
public interface FaqsService {
    Faqs getFaqsById(int id);
    List<Faqs> getAll(Map<String, String> params);
    List<Faqs> getTop5Recent();
    Faqs add(Faqs f, String email);
    boolean update(Faqs f);
    boolean delete(Faqs f);
}
