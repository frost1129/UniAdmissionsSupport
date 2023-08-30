/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.Banner;
import com.linhv.repository.BannerRepository;
import com.linhv.service.BannerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class BannerServiceImpl implements BannerService{
    
    @Autowired
    private BannerRepository bannerRepo;

    @Override
    public List<Banner> getBanners() {
        return this.bannerRepo.getAllBanners();
    }

}
