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

    public ResponseEntity<CommonResponse> addUser(User user){
        CommonResponse cr = new CommonResponse();

        if(!userRepository.existsByEmail(user.getEmail())){
            userRepository.createUser(user.getEmail(),user.getPassword(),(byte)(user.getIsAdmin()?1:0));
            cr.msg = "User with email: " + user.getEmail() + " was created successfully.";
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

        Optional<User> optionalUser = Optional.ofNullable(userRepository.getUserById(id));

        if(optionalUser.isPresent()){
            cr.data = userRepository.getUserById(id);
            cr.msg = "User with id: " + id + " was found.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "User with id: " + id + " was not found.";
            cr.status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updateUserIsAdmin(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<User> optionalUser = Optional.ofNullable(userRepository.getUserById(id));

        if(optionalUser.isPresent()){
            boolean currentAdminStatus = !optionalUser.get().getIsAdmin();
            cr.data = userRepository.updateIsAdmin(id, (byte)(currentAdminStatus?1:0));
            cr.msg = "The user's admin status was updated to: " + currentAdminStatus;
            cr.status = HttpStatus.OK;
        }
        else{
            cr.msg = "User with id: " + id + " was not found.";
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    //public ResponseEntity<CommonResponse> deleteUser(Long id){}

    public ResponseEntity<CommonResponse> GetAllUsers(){
        CommonResponse cr = new CommonResponse();
        cr.data = userRepository.GetAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all users";

        return new ResponseEntity<>(cr, cr.status);
    }
}
