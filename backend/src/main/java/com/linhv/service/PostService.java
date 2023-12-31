/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.Post;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prodi
 */
public interface PostService {
    Post getPostById(String id);
    boolean addPost(Post post);
    boolean updatePost(Post post);
    boolean deletePost(String id);
    List<Post> getAllPostByAdmission(int admissionId, Map<String, String> params);
    List<Post> get5PostByAdmission(int admissionId);
    List<Post> getAllPost(Map<String, String> params);
    List<Post> getAllLivestream(Map<String, String> params);
    List<Post> getAll(Map<String, String> params);
    List<String> getAllTitle();
    Long countAll(Map<String, String> params);
    Long countByAdmission(int admissionId);
    Long countPost();
    Long countLivestream();
    List<Post> getAllByUserId(int id);
}
