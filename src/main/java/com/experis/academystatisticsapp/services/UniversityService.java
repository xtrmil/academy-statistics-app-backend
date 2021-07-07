package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.University;
import com.experis.academystatisticsapp.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniversityService {

    @Autowired
    UniversityRepository universityRepository;

    public ResponseEntity<CommonResponse> createUniversity(University university){
        CommonResponse cr = new CommonResponse();

        if(!universityRepository.existsById(university.getId())){
            universityRepository.createUniversity(university.getName());
            cr.msg = "University: " + university.getName() + " was added successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "University: " + university.getName() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getUniversityById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<University> optionalUniversity = Optional.ofNullable(universityRepository.getUniversityById(id));

        if(optionalUniversity.isPresent()){
            cr.data = universityRepository.getUniversityById(id);
            cr.msg = "University with id: " + id + " was found.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "University with id: " + id + " was not found.";
            cr.status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updateUniversityById(Long id, University universityToUpdate){
        CommonResponse cr = new CommonResponse();

        Optional<University> optionalUniversity = Optional.ofNullable(universityRepository.getUniversityById(id));

        if(optionalUniversity.isPresent()){
            University university = optionalUniversity.get();

            if(universityToUpdate.getName() != null){
                university.setName(universityToUpdate.getName());
            }

            universityRepository.updateUniversityById(university.getId(), university.getName());
            cr.data = university;
            cr.msg = "University: "  + university.getName() + " was updated successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Unable to update university by name: " + universityToUpdate.getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deleteUniversityById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<University> optionalUniversity = Optional.ofNullable(universityRepository.getUniversityById(id));

        if(optionalUniversity.isPresent()){
            universityRepository.deleteUniversityById(id);
            cr.data = optionalUniversity;
            cr.msg = "University: " + optionalUniversity.get().getName() + " was deleted successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.data = optionalUniversity;
            cr.msg = "Unable to delete university by name: " + optionalUniversity.get().getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAllUniversities(){
        CommonResponse cr = new CommonResponse();
        cr.data = universityRepository.GetAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all universities.";

        return new ResponseEntity<>(cr, cr.status);
    }
}
