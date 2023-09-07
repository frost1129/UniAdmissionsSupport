/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.PostComment;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface PostCommentRepository {
    List<PostComment> getAllByPostId(String id);
    List<PostComment> getSubsByCommentId(String id);
    PostComment getById(String id);
    PostComment add(PostComment comment);
    boolean update(PostComment comment);
    boolean delete(PostComment comment);
}
