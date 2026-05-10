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

    @PostMapping("/signup/verify")
    public  ResponseEntity<Map<String,Object>> verifySignupOtp(@RequestBody OtpRequestDTO otp){
        return as.verifySignup(otp.getOtp());
    }

    @PostMapping("/change-password")
     public  ResponseEntity<Map<String,Object>> changePassword(@RequestHeader("X-User-Id") String id,@RequestBody ChangePasswordDTO changePasswordDTO ){
        return as.changePassword(id,changePasswordDTO);
    }

    @PostMapping("/forgot-password")
     public  ResponseEntity<Map<String,Object>> forgotPassword(@RequestBody UserLoginDTO uld) throws MessagingException {
        return as.forgotPassword(uld);
    }

    @PostMapping("/forgot-password/verify")
    public  ResponseEntity<Map<String,Object>> verifyForgotPasswordOtp(@RequestBody  ForgotPasswordDTO forgotPasswordDTO){
        return as.verifyForgotPassword(forgotPasswordDTO);
    }

    @PostMapping("/login/google")
    public  ResponseEntity<Map<String,Object>> loginWithGoogle(@RequestBody UserRequestDTO userRequestDTO){
        return as.loginWithGoogle(userRequestDTO);
    }

    @PostMapping("/signup/google")
    public  ResponseEntity<Map<String,Object>> signupWithGoogle(@RequestBody UserRequestDTO userRequestDTO){
        return  as.signupWithGoogle(userRequestDTO);
    }

}
