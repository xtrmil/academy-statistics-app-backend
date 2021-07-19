package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.DevLanguage;
import com.experis.academystatisticsapp.repositories.DevLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DevLanguageService {

    @Autowired
    DevLanguageRepository devLanguageRepository;

    public ResponseEntity<CommonResponse> createDevLanguage(DevLanguage devLanguage){
        CommonResponse cr = new CommonResponse();

        if(!devLanguageRepository.existsByName(devLanguage.getName())){
            devLanguageRepository.createDevLanguage(devLanguage.getName());
            cr.msg = "Development language " + devLanguage.getName() + " was added successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "Development language: " + devLanguage.getName() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getDevLanguageById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<DevLanguage> devLanguage = Optional.ofNullable(devLanguageRepository.getDevLanguageById(id));

        if(devLanguage.isPresent()){
            cr.data = devLanguageRepository.getDevLanguageById(id);
            cr.msg = "Dev language with id: " + id + " was found.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Dev language with id: " + id + " was not found.";
            cr.status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updateDevLanguageById(Long id, DevLanguage devLanguageToUpdate){
        CommonResponse cr = new CommonResponse();

        Optional<DevLanguage> optionalDevLanguage = Optional.ofNullable(devLanguageRepository.getDevLanguageById(id));

        if(optionalDevLanguage.isPresent()){
            DevLanguage devLanguage = optionalDevLanguage.get();

            if(devLanguageToUpdate.getName() != null){
                devLanguage.setName(devLanguageToUpdate.getName());
            }

            devLanguageRepository.updateDevLanguageById(devLanguage.getId(), devLanguage.getName());
            cr.data = devLanguage;
            cr.msg = "Dev language was updated to: "  + devLanguage.getName();
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Unable to update dev language with name : " + devLanguageToUpdate.getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deleteDevLanguageById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<DevLanguage> optionalDevLanguage = Optional.ofNullable(devLanguageRepository.getDevLanguageById(id));

        if(optionalDevLanguage.isPresent()){
            devLanguageRepository.deleteDevLanguageById(id);
            cr.data = optionalDevLanguage;
            cr.msg = "dev language with name: " + optionalDevLanguage.get().getName() + " was deleted successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.data = optionalDevLanguage;
            cr.msg = "Unable to delete dev language with name: " + optionalDevLanguage.get().getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAllDevLanguages(){
        CommonResponse cr = new CommonResponse();
        cr.data = devLanguageRepository.getAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all users";

        return new ResponseEntity<>(cr, cr.status);
    }
}
