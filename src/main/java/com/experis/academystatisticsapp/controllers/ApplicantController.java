package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.Applicant;
import com.experis.academystatisticsapp.services.ApplicantService;
import com.experis.academystatisticsapp.services.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value = "api/v1/applicant")
public class ApplicantController {

    @Autowired
    ApplicantService applicantService;

    @PostMapping("")
    public ResponseEntity<CommonResponse> createApplicant(@RequestBody Applicant applicant){
        return applicantService.createApplicant(applicant);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getApplicantById(@PathVariable Long id) {
        return applicantService.getApplicantById(id);
    }
    @PreAuthorize("hasRole('ROLE_Liffa')")
    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllApplicants(){
        return applicantService.getAllApplicants();
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponse> updateApplicant(@PathVariable Long id, @RequestBody Applicant applicantToUpdate) {
        return applicantService.updateApplicantById(id, applicantToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deleteApplicant(@PathVariable Long id){
        return applicantService.deleteApplicantById(id);
    }
}
