package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.PersonalityTestScore;
import com.experis.academystatisticsapp.repositories.PersonalityTestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalityTestScoreService {

    @Autowired
    PersonalityTestScoreRepository ptsRepository;

    public ResponseEntity<CommonResponse> createPersonalityTestScore(PersonalityTestScore personalityTestScore){
        CommonResponse cr = new CommonResponse();

        if(!ptsRepository.existsById(personalityTestScore.getId())){
            ptsRepository.createPersonalityTestScore(personalityTestScore.getScore(),
                    personalityTestScore.getTestDate(), personalityTestScore.getApplicant().getId());

            cr.msg = "Applicant: " + personalityTestScore.getId() + " was added successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "Applicant: " + personalityTestScore.getId() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getPersonalityTestScoreById(Long id){
        CommonResponse cr = new CommonResponse();
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updatePersonalityTestScoreById(Long id, PersonalityTestScore updatedPersonalityTestScore){
        CommonResponse cr = new CommonResponse();
        Optional<PersonalityTestScore> optionalPersonalityTestScore = Optional.ofNullable(ptsRepository.getPersonalityTestScoreById(id));

        if(optionalPersonalityTestScore.isPresent()){
            PersonalityTestScore testScore = optionalPersonalityTestScore.get();

            if(updatedPersonalityTestScore.getScore() != null){
                testScore.setScore(updatedPersonalityTestScore.getScore());
            }
            if(updatedPersonalityTestScore.getTestDate() != null){
                testScore.setTestDate(updatedPersonalityTestScore.getTestDate());
            }
            ptsRepository.updatePersonalityTestScore(testScore.getScore(), testScore.getTestDate(), testScore.getApplicant().getId());
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deletePersonalityTestScoreById(Long id){
        CommonResponse cr = new CommonResponse();
        Optional<PersonalityTestScore> optionalPersonalityTestScore = Optional.ofNullable(ptsRepository.getPersonalityTestScoreById(id));

        if(optionalPersonalityTestScore.isPresent()){
            ptsRepository.deleteTestScoreById(id);
            cr.data = optionalPersonalityTestScore;
            cr.msg = "TestScore: " + optionalPersonalityTestScore.get() + " was deleted successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.data = optionalPersonalityTestScore;
            cr.msg = "Unable to delete test score: " + optionalPersonalityTestScore.get().getId();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAllPersonalityTestScores(){
        CommonResponse cr = new CommonResponse();
        cr.data = ptsRepository.GetAllPersonalityTestScores();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all personality test scores.";

        return new ResponseEntity<>(cr, cr.status);
    }

}
