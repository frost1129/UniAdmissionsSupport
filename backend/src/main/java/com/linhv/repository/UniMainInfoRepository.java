/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.UniMainInfo;

/**
 *
 * @author prodi
 */
public interface UniMainInfoRepository {
    UniMainInfo getUniInfo(String id);
    UniMainInfo updateUniInfo(UniMainInfo info);
}
