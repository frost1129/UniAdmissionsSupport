/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.service;

import com.linhv.pojo.Branch;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface BranchService {
    List<Branch> getBranches();
    Branch add(Branch branch);
    Branch update(Branch branch);
    boolean delete(Branch branch);
    Branch getBranchById(int id);
}
