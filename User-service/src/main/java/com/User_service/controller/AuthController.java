package com.User_service.controller;

import com.User_service.DTO.UserLoginDTO;
import com.User_service.DTO.UserRequestDTO;
import com.User_service.DTO.UserResponseDTO;
import com.User_service.DTO.UserSignupDTO;
import com.User_service.repository.AuthRepo;
import com.User_service.services.AuthService;
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
    public ResponseEntity<Map<String,Object>> signup(@RequestBody UserSignupDTO usd){
       return as.signup(usd);
    }

}
