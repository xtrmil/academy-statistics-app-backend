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
    public ResponseEntity<CommonResponse> createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("{userId}")
    public ResponseEntity<CommonResponse> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("{userId}")
    public ResponseEntity<CommonResponse> updateUserIsAdmin(@PathVariable Long userId) {
        return userService.updateUserIsAdmin(userId);
    }

    /*
    @PutMapping("{userId}")
    public ResponseEntity<CommonResponse> updateUser(@PathVariable User user) {
        return null;
    }
     */

    @DeleteMapping("{userId}")
    public ResponseEntity<CommonResponse> deleteUser(@PathVariable Long userId){
        return null;
    }

    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllUsers(){
        return userService.GetAllUsers();
    }

    /*
    @PutMapping("{userId}")
    public ResponseEntity<CommonResponse> updateUserPassword(@PathVariable Long userId) {
        return null;
    }
     */

}

