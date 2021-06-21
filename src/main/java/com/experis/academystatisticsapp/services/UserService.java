package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.User;
import com.experis.academystatisticsapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<CommonResponse> createUser(User user){
        CommonResponse cr = new CommonResponse();
        if(!userRepository.existsByEmail(user.getEmail())){
            cr.data = userRepository.addUser(user.getEmail(), user.getPassword());
            cr.msg = "User with id:" + user.getId() + " was created successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "User with email: " + user.getEmail() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }

        return new ResponseEntity<>(cr, cr.status);
    }
}
