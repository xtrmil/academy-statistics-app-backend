package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.Assignment;
import com.experis.academystatisticsapp.models.Location;
import com.experis.academystatisticsapp.models.PersonalityTestScore;
import com.experis.academystatisticsapp.repositories.PersonalityTestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonalityTestScoreService {

    @Autowired
    PersonalityTestScoreRepository ptsRepository;
    public ResponseEntity<CommonResponse> createPersonalityTestScore(PersonalityTestScore personalityTestScore){
        CommonResponse cr = new CommonResponse();
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getPersonalityTestScoreById(Long id){
        CommonResponse cr = new CommonResponse();
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updatePersonalityTestScoreById(Long id, PersonalityTestScore ptsToUpdate){
        CommonResponse cr = new CommonResponse();
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deletePersonalityTestScoreById(Long id){
        CommonResponse cr = new CommonResponse();
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAllPersonalityTestScores(){
        CommonResponse cr = new CommonResponse();
        cr.data = ptsRepository.GetAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all personality test scores.";

        return new ResponseEntity<>(cr, cr.status);
    }

}
