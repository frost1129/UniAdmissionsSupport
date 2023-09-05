/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.Faculty;
import com.linhv.pojo.FacultyPost;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface FacultyService {
    List<Faculty> getAll();
    Faculty getFacultyById(int id);
    boolean add(Faculty f);
    boolean update(Faculty f);
    boolean delete(int id);
    
    FacultyPost getFacultyPostById(int id);
    boolean addPost(FacultyPost fp);
    boolean updatePost(FacultyPost fp);
}
