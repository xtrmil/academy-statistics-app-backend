package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.User;
import com.experis.academystatisticsapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<CommonResponse> createUser(User user){
        CommonResponse cr = new CommonResponse();

        if(!userRepository.existsByEmail(user.getEmail())){
            userRepository.addUser(user.getEmail(),user.getPassword(),(byte)(user.getIsAdmin()?1:0));
            cr.msg = "User with id:" + user.getId() + " was created successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "User with email: " + user.getEmail() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }

        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getUser(Long id){
        CommonResponse cr = new CommonResponse();
        userRepository.getUserById(id);
        cr.data = userRepository.getUserById(id);
        cr.status = HttpStatus.OK;


        return new ResponseEntity<>(cr, cr.status);
    }
}
