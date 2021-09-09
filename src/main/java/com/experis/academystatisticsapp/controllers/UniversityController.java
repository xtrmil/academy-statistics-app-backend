package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.University;
import com.experis.academystatisticsapp.services.CommonResponse;
import com.experis.academystatisticsapp.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/university")
public class UniversityController {

    @Autowired
    UniversityService universityService;


    @PostMapping("")
    public ResponseEntity<CommonResponse> addUniversity(@RequestBody University university){
        return universityService.createUniversity(university);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getUniversityById(@PathVariable Long id) {
        return universityService.getUniversityById(id);
    }

    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllUniversities(){
        return universityService.getAllUniversities();
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponse> updateUniversity(@PathVariable Long id, @RequestBody University universityToUpdate) {
        return universityService.updateUniversityById(id, universityToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deleteaddUniversity(@PathVariable Long id){
        return universityService.deleteUniversityById(id);
    }


}
