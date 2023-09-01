/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.Banner;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface BannerService {
    List<Banner> getBanners();
    Banner add(Banner banner);
    boolean update(Banner banner);
    boolean delete(Banner banner);
    Banner getBannerById(int id);
}
