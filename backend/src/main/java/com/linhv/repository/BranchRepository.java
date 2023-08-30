/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.linhv.repository;

import com.linhv.pojo.Branch;
import java.util.List;

/**
 *
 * @author prodi
 */
public interface BranchRepository {
    Branch add(Branch branch);
    Branch update(Branch branch);
    boolean delete(Branch branch);
    Branch getBranchById(int id);
    List<Branch> getAllBranches();
}
