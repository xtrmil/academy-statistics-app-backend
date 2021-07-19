package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.PersonalityTestScore;
import com.experis.academystatisticsapp.services.CommonResponse;
import com.experis.academystatisticsapp.services.PersonalityTestScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/personality")
public class PersonalityTestScoreController {

    @Autowired
    PersonalityTestScoreService ptsService;

    @PostMapping("")
    public ResponseEntity<CommonResponse> createPersonalityTestScore(@RequestBody PersonalityTestScore personalityTestScore){
        return ptsService.createPersonalityTestScore(personalityTestScore);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getPersonalityTestScoreById(@PathVariable Long id) {
        return ptsService.getPersonalityTestScoreById(id);
    }

    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllPersonalityTestScores(){
        return ptsService.getAllPersonalityTestScores();
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponse> updatePersonalityTestScore(@PathVariable Long id, @RequestBody PersonalityTestScore ptsToUpdate) {
        return ptsService.updatePersonalityTestScoreById(id, ptsToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deletePersonalityTestScore(@PathVariable Long id){
        return ptsService.deletePersonalityTestScoreById(id);
    }
}
