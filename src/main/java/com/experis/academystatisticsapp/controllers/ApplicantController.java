package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.Applicant;
import com.experis.academystatisticsapp.services.ApplicantService;
import com.experis.academystatisticsapp.services.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicantController {

    @Autowired
    ApplicantService applicantService;

    @PostMapping("")
    public ResponseEntity<CommonResponse> addEducation(@RequestBody Applicant applicant){
        return applicantService.createApplicant(applicant);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getEducation(@PathVariable Long id) {
        return applicantService.getApplicantById(id);
    }

    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllEducations(){
        return applicantService.getAllApplicants();
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponse> updateEducation(@PathVariable Long id, @RequestBody Applicant applicantToUpdate) {
        return applicantService.updateApplicantById(id, applicantToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deleteEducation(@PathVariable Long id){
        return applicantService.deleteApplicantById(id);
    }
}
