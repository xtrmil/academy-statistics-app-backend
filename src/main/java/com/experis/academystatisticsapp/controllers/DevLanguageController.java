package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.DevLanguage;
import com.experis.academystatisticsapp.services.CommonResponse;
import com.experis.academystatisticsapp.services.DevLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/language")
public class DevLanguageController {

    @Autowired
    DevLanguageService devLanguageService;

    @PostMapping("")
    public ResponseEntity<CommonResponse> addDevLanguage(@RequestBody DevLanguage devLanguage){
        return devLanguageService.createDevLanguage(devLanguage);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getDevLanguage(@PathVariable Long id) {
        return devLanguageService.getDevLanguageById(id);
    }

    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllDevLanguages(){
        return devLanguageService.getAllDevLanguages();
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponse> updateDevLanguage(@PathVariable Long id, @RequestBody DevLanguage devLanguageToUpdate) {
        return devLanguageService.updateDevLanguageById(id, devLanguageToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deleteDevLanguage(@PathVariable Long id){
        return devLanguageService.deleteDevLanguageById(id);
    }
}
