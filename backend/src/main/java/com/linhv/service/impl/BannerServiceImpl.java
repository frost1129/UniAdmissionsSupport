/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.linhv.pojo.Banner;
import com.linhv.repository.BannerRepository;
import com.linhv.service.BannerService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Banner> getBanners() {
        return this.bannerRepo.getAllBanners();
    }

    @Override
    public Banner add(Banner banner) {
        if (!banner.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(banner.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                banner.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(BannerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.bannerRepo.add(banner);
    }

    @Override
    public boolean update(Banner banner) {
        return this.bannerRepo.update(banner);
    }

    @Override
    public boolean delete(Banner banner) {
        return this.bannerRepo.delete(banner);
    }

    @Override
    public Banner getBannerById(int id) {
        return this.bannerRepo.getBannerById(id);
    }

}
