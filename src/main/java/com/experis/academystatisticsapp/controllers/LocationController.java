package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.Location;
import com.experis.academystatisticsapp.services.CommonResponse;
import com.experis.academystatisticsapp.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping("")
    public ResponseEntity<CommonResponse> addLocation(@RequestBody Location location){
        return locationService.createLocation(location);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getLocation(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllLocations(){
        return locationService.getAllLocations();
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponse> updateLocation(@PathVariable Long id, @RequestBody Location locationToUpdate) {
        return locationService.updateLocationById(id, locationToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deleteLocation(@PathVariable Long id){
        return locationService.deleteLocationById(id);
    }
}
