package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.User;
import com.experis.academystatisticsapp.services.CommonResponse;
import com.experis.academystatisticsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<CommonResponse> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("{userId}")
    public ResponseEntity<CommonResponse> updateUserPassword(@PathVariable Long userId){
        return null;
    }
}
