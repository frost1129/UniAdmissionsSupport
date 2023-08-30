/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.Post;

/**
 *
 * @author prodi
 */
public interface PostRepository {
    Post getPostById(String id);
    Post addPost(Post post);
}
