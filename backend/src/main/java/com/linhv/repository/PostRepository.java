/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.Post;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface PostRepository {
    Post getPostById(String id);
    boolean addPost(Post post);
    boolean updatePost(Post post);
    boolean detelePost(Post post);
    List<Post> getAllPostByAdmission(int admissionId);
    List<Post> get5PostByAdmission(int admissionId);
}
