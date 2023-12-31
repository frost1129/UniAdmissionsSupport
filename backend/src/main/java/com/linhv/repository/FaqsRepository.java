/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.Faqs;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prodi
 */
public interface FaqsRepository {
    Faqs getFaqsById(int id);
    List<Faqs> getAll(Map<String, String> params);
    List<Faqs> getTop5Recent();
    Faqs add(Faqs f);
    boolean update(Faqs f);
    boolean delete(Faqs f);
    Long countAll();
    Long countAllByParams(Map<String, String> params);
    List<Faqs> getAllByUserId(int id);
}
