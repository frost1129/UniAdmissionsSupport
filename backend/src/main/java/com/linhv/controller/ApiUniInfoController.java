/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.UniMainInfo;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prodi
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ApiUniInfoController {
    
    @Autowired
    LocalSessionFactoryBean factory;
    
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
    
    @GetMapping("/info")
    public ResponseEntity<UniMainInfo> getUniInfo() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("from UniMainInfo where id=:id");
        q.setParameter("id", "HCMOU");
      
        return new ResponseEntity<>((UniMainInfo) q.getSingleResult(), HttpStatus.OK);
    }
}
