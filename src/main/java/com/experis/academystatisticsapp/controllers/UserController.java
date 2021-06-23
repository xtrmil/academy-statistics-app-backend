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

    @PutMapping("admin/{userId}")
    public ResponseEntity<CommonResponse> updateUserIsAdmin(@PathVariable Long userId) {
        return userService.updateUserIsAdmin(userId);
    }

    @PutMapping("{userId}")
    public ResponseEntity<CommonResponse> updateUser(@PathVariable Long userId, @RequestBody User userToUpdate) {
        return userService.updateUser(userId, userToUpdate);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<CommonResponse> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
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

