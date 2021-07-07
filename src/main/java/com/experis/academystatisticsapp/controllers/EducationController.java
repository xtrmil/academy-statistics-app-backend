package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.Education;
import com.experis.academystatisticsapp.services.CommonResponse;
import com.experis.academystatisticsapp.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/education")
public class EducationController {

    @Autowired
    EducationService educationService;

    @PostMapping("")
    public ResponseEntity<CommonResponse> addEducation(@RequestBody Education education){
        return educationService.createEducation(education);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getEducation(@PathVariable Long id) {
        return educationService.getEducationById(id);
    }

    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllEducations(){
        return educationService.getAllEducations();
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponse> updateEducation(@PathVariable Long id, @RequestBody Education educationToUpdate) {
        return educationService.updateEducationById(id, educationToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deleteEducation(@PathVariable Long id){
        return educationService.deleteEducationById(id);
    }
}
