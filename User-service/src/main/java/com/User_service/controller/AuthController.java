package com.User_service.controller;

import com.User_service.DTO.*;
import com.User_service.repository.AuthRepo;
import com.User_service.services.AuthService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService as;


    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserLoginDTO uld){
        return as.login(uld);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String,Object>> signup(@RequestBody UserSignupDTO usd) throws MessagingException {
       return as.signup(usd);
    }

    @PostMapping("/otp/verify")
    public  ResponseEntity<Map<String,Object>> verifyOtp(@RequestBody OtpRequestDTO otp){
        return as.verifyOtp(otp.getOtp());
    }

}
