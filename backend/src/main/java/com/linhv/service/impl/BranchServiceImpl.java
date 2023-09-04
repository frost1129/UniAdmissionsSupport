/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.Branch;
import com.linhv.repository.BranchRepository;
import com.linhv.service.BranchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class BranchServiceImpl implements BranchService{
    
    @Autowired
    private BranchRepository branchRepo;

    @Override
    public List<Branch> getBranches() {
        return this.branchRepo.getAllBranches();
    }

    @Override
    public Branch add(Branch branch) {
        return this.branchRepo.add(branch);
    }

    @Override
    public Branch update(Branch branch) {
        return this.branchRepo.update(branch);
    }

    @Override
    public boolean delete(Branch branch) {
        return this.branchRepo.delete(branch);
    }

    @Override
    public Branch getBranchById(int id) {
        return this.branchRepo.getBranchById(id);
    }

}
