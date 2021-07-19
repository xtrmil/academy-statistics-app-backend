package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.Applicant;
import com.experis.academystatisticsapp.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ApplicantService {

    @Autowired
    ApplicantRepository applicantRepository;

    /**TODO*/
    public ResponseEntity<CommonResponse> createApplicant(Applicant applicant){
        CommonResponse cr = new CommonResponse();

        if(!applicantRepository.existsById(applicant.getId())){
            applicantRepository.createApplicant(applicant.getClsScore(),
                    applicant.getInterviewScore(),
                    applicant.isMovable(),
                    applicant.getRelativeClsScore(),
                    applicant.getRelativeSwitchScore(),
                    applicant.getStudiedSemesters(),
                    applicant.getSwitchScore());

            cr.msg = "Applicant: " + applicant.getId() + " was added successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "Applicant: " + applicant.getId() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getApplicantById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<Applicant> applicant = Optional.ofNullable(applicantRepository.getApplicantById(id));

        if(applicant.isPresent()){
            cr.data = applicantRepository.getApplicantById(id);
            cr.msg = "Applicant with id: " + id + " was found.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Applicant with id: " + id + " was not found.";
            cr.status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    /**TODO*/
    public ResponseEntity<CommonResponse> updateApplicantById(Long id, Applicant updatedApplicant){
        CommonResponse cr = new CommonResponse();
        Optional<Applicant> optionalApplicant = Optional.ofNullable(applicantRepository.getApplicantById(id));

        if(optionalApplicant.isPresent()){
            Applicant applicant = optionalApplicant.get();


            if(updatedApplicant.getInterviewScore() != null){
                applicant.setInterviewScore(updatedApplicant.getInterviewScore());
            }
            if(updatedApplicant.isMovable() != applicant.isMovable()){
                applicant.setMovable(updatedApplicant.isMovable());
            }
            if(updatedApplicant.getClsScore() != null){
                applicant.setClsScore(updatedApplicant.getClsScore());
            }
            if(updatedApplicant.getRelativeClsScore() != null){
                applicant.setRelativeClsScore(updatedApplicant.getRelativeClsScore());
            }
            if(updatedApplicant.getSwitchScore() != null){
                applicant.setSwitchScore(updatedApplicant.getSwitchScore());
            }
            if(updatedApplicant.getRelativeSwitchScore() != null){
                applicant.setRelativeSwitchScore(updatedApplicant.getRelativeSwitchScore());
            }
            if(updatedApplicant.getStudiedSemesters() != null){
                applicant.setStudiedSemesters(updatedApplicant.getStudiedSemesters());
            }

            applicantRepository.updateApplicantById(applicant.getClsScore(),
                    applicant.getInterviewScore(),
                    applicant.isMovable(),
                    applicant.getRelativeClsScore(),
                    applicant.getRelativeSwitchScore(),
                    applicant.getStudiedSemesters(),
                    applicant.getSwitchScore());

            cr.data = applicant;
            cr.msg = "Applicant: "  + applicant.getId() + " was updated successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Unable to update applicant: " + updatedApplicant.getId();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deleteApplicantById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<Applicant> optionalApplicant = Optional.ofNullable(applicantRepository.getApplicantById(id));

        if(optionalApplicant.isPresent()){
            applicantRepository.deleteApplicantById(id);
            cr.data = optionalApplicant;
            cr.msg = "Applicant: " + optionalApplicant.get() + " was deleted successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.data = optionalApplicant;
            cr.msg = "Unable to delete applicant: " + optionalApplicant.get().getId();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAllApplicants(){
        CommonResponse cr = new CommonResponse();
        cr.data = applicantRepository.GetAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all applicants.";

        return new ResponseEntity<>(cr, cr.status);
    }
}
