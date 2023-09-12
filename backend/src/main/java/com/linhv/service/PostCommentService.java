/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.PostComment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prodi
 */
public interface PostCommentService {
    List<PostComment> getAllByPostId(String id);
    List<PostComment> getSubsByCommentId(String id);
    PostComment getById(String id);
    PostComment add(Map<String, String> params);
    boolean update(Map<String, String> params);
    boolean delete(String id);
    int countCommentByPostId(String id);
    List<PostComment> getAllByUserId(int id);
}
