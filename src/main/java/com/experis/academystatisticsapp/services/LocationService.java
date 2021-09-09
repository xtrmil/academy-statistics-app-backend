package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.Location;
import com.experis.academystatisticsapp.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public ResponseEntity<CommonResponse> createLocation(Location location){
        CommonResponse cr = new CommonResponse();

        if(!locationRepository.existsByName(location.getName())){
            locationRepository.createLocation(location.getName());
            cr.msg = "Location: " + location.getName() + " was added successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "Location: " + location.getName() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getLocationById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<Location> location = Optional.ofNullable(locationRepository.getLocationById(id));

        if(location.isPresent()){
            cr.data = locationRepository.getLocationById(id);
            cr.msg = "Location with id: " + id + " was found.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Location with id: " + id + " was not found.";
            cr.status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updateLocationById(Long id, Location locationToUpdate){
        CommonResponse cr = new CommonResponse();

        Optional<Location> optionalLocation = Optional.ofNullable(locationRepository.getLocationById(id));

        if(optionalLocation.isPresent()){
            Location location = optionalLocation.get();

            if(locationToUpdate.getName() != null){
                location.setName(locationToUpdate.getName());
            }
            locationRepository.updateLocationById(location.getId(), location.getName());
            cr.data = location;
            cr.msg = "Location: "  + location.getName() + " was updated successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Unable to update location: " + locationToUpdate.getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deleteLocationById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<Location> optionalLocation = Optional.ofNullable(locationRepository.getLocationById(id));

        if(optionalLocation.isPresent()){
            locationRepository.deleteLocationById(id);
            cr.data = optionalLocation;
            cr.msg = "Location: " + optionalLocation.get().getName() + " was deleted successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.data = optionalLocation;
            cr.msg = "Unable to delete location: " + optionalLocation.get().getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAllLocations(){
        CommonResponse cr = new CommonResponse();
        cr.data = locationRepository.getAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all locations";

        return new ResponseEntity<>(cr, cr.status);
    }
}
