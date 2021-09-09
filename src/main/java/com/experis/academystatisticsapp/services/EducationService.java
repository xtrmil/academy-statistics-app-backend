package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.Education;
import com.experis.academystatisticsapp.repositories.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    public ResponseEntity<CommonResponse> createEducation(Education education){
        CommonResponse cr = new CommonResponse();

        if(!educationRepository.existsById(education.getId())){
            educationRepository.createEducation(education.getName());
            cr.msg = "Education " + education.getName() + " was added successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "Education: " + education.getName() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getEducationById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<Education> optionalEducation = Optional.ofNullable(educationRepository.getEducationById(id));

        if(optionalEducation.isPresent()){
            cr.data = educationRepository.getEducationById(id);
            cr.msg = "Education with id: " + id + " was found.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Education with id: " + id + " was not found.";
            cr.status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updateEducationById(Long id, Education educationToUpdate){
        CommonResponse cr = new CommonResponse();

        Optional<Education> optionalEducation = Optional.ofNullable(educationRepository.getEducationById(id));

        if(optionalEducation.isPresent()){
            Education education = optionalEducation.get();

            if(educationToUpdate.getName() != null){
                education.setName(educationToUpdate.getName());
            }

            educationRepository.updateEducationById(education.getId(), education.getName());
            cr.data = education;
            cr.msg = "Education: "  + education.getName() + " was updated successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Unable to update education by name: " + educationToUpdate.getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deleteEducationById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<Education> optionalEducation = Optional.ofNullable(educationRepository.getEducationById(id));

        if(optionalEducation.isPresent()){
            educationRepository.deleteEducationById(id);
            cr.data = optionalEducation;
            cr.msg = "Education: " + optionalEducation.get().getName() + " was deleted successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.data = optionalEducation;
            cr.msg = "Unable to delete education by name: " + optionalEducation.get().getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAllEducations(){
        CommonResponse cr = new CommonResponse();
        cr.data = educationRepository.getAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all educations.";

        return new ResponseEntity<>(cr, cr.status);
    }
}
