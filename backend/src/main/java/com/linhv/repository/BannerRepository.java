/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.Banner;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface BannerRepository {
    Banner add(Banner banner);
    Banner update(Banner banner);
    boolean delete(Banner banner);
    Banner getBannerById(int id);
    List<Banner> getAllBanners();
}
