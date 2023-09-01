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
    boolean detelePost(Post post);
    List<Post> getAllPostByAdmission(int admissionId);
    List<Post> get5PostByAdmission(int admissionId);
    List<Post> getAllPost();
    List<Post> getAllLivestream();
    List<Post> getAll(Map<String, String> params);
}
