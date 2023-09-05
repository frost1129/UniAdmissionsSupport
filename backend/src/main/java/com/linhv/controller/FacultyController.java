/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.controller;

import com.linhv.pojo.Faculty;
import com.linhv.pojo.FacultyPost;
import com.linhv.service.AdmissionScoreService;
import com.linhv.service.FacultyService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author prodi
 */
@Controller
@RequestMapping("/admin/faculties")
public class FacultyController {
    
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private AdmissionScoreService scoreService;
    
    @GetMapping("")
    public String listFaculties(Model model) {
        model.addAttribute("faculties", this.facultyService.getAll());
        
        return "faculties";
    }
    
    @GetMapping("/{id}")
    public String facultyDetail(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("faculty", this.facultyService.getFacultyById(id));
        
        return "faculty-detail";
    }
    
    @GetMapping("/detail")
    public String addFaculty(Model model) {
        model.addAttribute("faculty", new Faculty());
        
        return "faculty-detail";
    }
    
    @PostMapping("/add-or-update")
    public String addOrUpdate(@ModelAttribute(value = "faculty") @Valid Faculty f,
                                BindingResult bs) {
        if (!bs.hasErrors()) {
            if (f.getId() != null)
                this.facultyService.update(f);
            else 
                this.facultyService.add(f);
            return "redirect:/admin/faculties";
        }
        return "faculty-detail";
    }
    
    @PostMapping("/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        this.facultyService.delete(id);
        return "redirect:/admin/faculties";
    }
    
    @GetMapping("/{id}/overview")
    public String fOverview(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("faculty", this.facultyService.getFacultyById(id));
        
        FacultyPost fp = this.facultyService.getFacultyPostById(id);
        if (fp != null)
            model.addAttribute("facultyPost", fp);
        else 
            model.addAttribute("facultyPost", new FacultyPost());
        
        return "f-overview";
    }
    
    @PostMapping("/{id}/overview")
    public String updateFOverview(@ModelAttribute(value = "facultyPost") @Valid FacultyPost fp,
                                BindingResult bs, 
                                @PathVariable(value = "id") int id) {
        if (!bs.hasErrors()) {
            if (fp.getId() != null)
                this.facultyService.updatePost(fp);
            else {
                fp.setId(id);
                this.facultyService.addPost(fp);
            }
            return "redirect:/admin/faculties";
        }
        return "f-overview";
    }
    
    @GetMapping("/{id}/scores")
    public String fScores(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("faculty", this.facultyService.getFacultyById(id));
        return "f-scores";
    }
    
    @GetMapping("/{id}/scores/{year}")
    public String fYearScore(Model model, 
                            @PathVariable(value = "id") int id, 
                            @PathVariable(value = "year") int year) {
        model.addAttribute("faculty", this.facultyService.getFacultyById(id));
        return "f-year-score";
    }
    
    @GetMapping("/{id}/scores/new")
    public String newYearScore(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("faculty", this.facultyService.getFacultyById(id));
        return "f-year-score";
    }
    
    @PostMapping("/{id}/scores/add-or-update")
    public String addOrUpdateYearScore(@PathVariable(value = "id") int id) {
        return "redirect:/admin/faculties/{id}/scores";
    }
}
