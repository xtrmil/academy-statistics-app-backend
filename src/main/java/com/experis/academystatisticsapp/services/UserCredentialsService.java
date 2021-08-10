package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.User;
import com.experis.academystatisticsapp.models.UserCredentials;
import com.experis.academystatisticsapp.repositories.UserRepository;
import com.experis.academystatisticsapp.utils.JwtUtil;
import com.experis.academystatisticsapp.utils.TotpManager;
import com.experis.academystatisticsapp.utils.web.VerifyRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCredentialsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TotpManager totpManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserCredentials loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.getUserByEmail(email));
        optionalUser.orElseThrow(() -> new UsernameNotFoundException(("Not found: "+ email)));
        return optionalUser.map(UserCredentials::new).get();
    }


    public ResponseEntity<CommonResponse> verifyLogin(VerifyRequest verifyRequest){

        CommonResponse cr =  new CommonResponse();
        Optional<User> optionalUser = Optional.ofNullable(userRepository.getUserByEmail(verifyRequest.getEmail()));
        if(optionalUser.isPresent()){
            User user  = optionalUser.get();
            if(!totpManager.verifyCode(verifyRequest.getCode(), user.getSecret())) {
                cr.msg = "Invalid code";
                cr.status = HttpStatus.BAD_REQUEST;
            }else{
                if(!user.getIsVerified()){
                    user.setIsVerified(true);
                    userRepository.save(user);
                }
                cr.data = jwtUtil.generateToken(new UserCredentials(user));
                cr.msg = "Verification successful";
                cr.status = HttpStatus.OK;
            }

        }else{
            cr.msg = "User not found";
            cr.status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(cr,cr.status);
    }
}
