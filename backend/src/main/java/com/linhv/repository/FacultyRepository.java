/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.Faculty;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface FacultyRepository {
    List<Faculty> getAll();
    Faculty getFacultyById(int id);
    boolean add(Faculty f);
    boolean update(Faculty f);
    boolean delete(int id);
}
