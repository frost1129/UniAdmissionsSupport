/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.Banner;
import com.linhv.pojo.Branch;
import com.linhv.pojo.UniMainInfo;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface UniMainInfoRepository {
    UniMainInfo getUniInfo(String id);
    List<Branch> getBranches();
    List<Banner> getBanners();
}
