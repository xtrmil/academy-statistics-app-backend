package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.User;
import com.experis.academystatisticsapp.services.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserController {

    @PostMapping("")
    public ResponseEntity<CommonResponse> createUser(@RequestBody User user){
        return null;
    }

    @PutMapping("{userId}")
    public ResponseEntity<CommonResponse> updateUserPassword(@PathVariable Long userId){
        return null;
    }
}
