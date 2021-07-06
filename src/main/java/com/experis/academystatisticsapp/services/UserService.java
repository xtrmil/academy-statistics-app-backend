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
            userRepository.createUser(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(),(byte)(user.getIsAdmin()?1:0));
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

    public ResponseEntity<CommonResponse> GetAllUsers(){
        CommonResponse cr = new CommonResponse();
        cr.data = userRepository.GetAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all users";

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

    public ResponseEntity<CommonResponse> updateUserPassword(Long id, String password){
        CommonResponse cr = new CommonResponse();

        Optional<User> optionalUser = Optional.ofNullable(userRepository.getUserById(id));

        if(optionalUser.isPresent()) {

            optionalUser.get().setPassword(password);
            userRepository.updatePasswordByUserId(id, optionalUser.get().getPassword());
            cr.data = optionalUser;
            cr.msg = "Password was updated successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Unable to update password for user with email: " + optionalUser.get().getEmail();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updateUser(Long id, User userToUpdate){
        CommonResponse cr = new CommonResponse();

        Optional<User> optionalUser = Optional.ofNullable(userRepository.getUserById(id));

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            if(userToUpdate.getFirstName() != null){
                user.setFirstName(userToUpdate.getFirstName());
            }

            if(userToUpdate.getLastName() != null){
                user.setLastName(userToUpdate.getLastName());
            }

            if(userToUpdate.getEmail() != null){
                user.setEmail(userToUpdate.getEmail());
            }
            userRepository.updateUserById(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
            cr.data = user;
            cr.msg = "User: " + user.getFirstName() + " " + user.getLastName() + " was updated.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Unable to update user with email : " + userToUpdate.getEmail();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deleteUser(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<User> optionalUser = Optional.ofNullable(userRepository.getUserById(id));

        if(optionalUser.isPresent()){
            userRepository.deleteUserById(id);
            cr.data = optionalUser;
            cr.msg = "User with email: " + optionalUser.get().getEmail() + " was deleted successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.data = optionalUser;
            cr.msg = "Unable to delete user with email: " + optionalUser.get().getEmail();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }


}
