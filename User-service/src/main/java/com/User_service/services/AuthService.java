package com.User_service.services;

import com.User_service.DTO.UserLoginDTO;
import com.User_service.DTO.UserProfileDTO;
import com.User_service.DTO.UserResponseDTO;
import com.User_service.DTO.UserSignupDTO;
import com.User_service.model.UserModel;
import com.User_service.repository.AuthRepo;
import com.User_service.utility.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthRepo ar;

    @Autowired
    private PasswordEncoder pe;

    @Autowired
    private ModelMapper mm;

    public ResponseEntity<Map<String,Object>> login(UserLoginDTO uld){
        UserModel um=ar.getUserByEmail(uld.getEmail());
        Map<String,Object> map=new HashMap<>();
        if(pe.matches(uld.getPassword(),um.getPassword())){
              String token= JwtUtil.generateToken(um.getId(),um.getEmail());
            UserProfileDTO upd=mm.map(um,UserProfileDTO.class);
           map.put("token",token);
           map.put("user",upd);
        }else{
            map.put("message","Invalid Credentials");
            return ResponseEntity.badRequest().body(map);
        }

        return ResponseEntity.ok(map);
    }

    public ResponseEntity<Map<String,Object>> signup(UserSignupDTO usd){
        UserModel um=mm.map(usd,UserModel.class);
        um.setPassword(pe.encode(usd.getPassword()));
        ar.save(um);
        Map<String,Object> map=new HashMap<>();
         map.put("message","Signup SuccessFull");
        return ResponseEntity.ok(map);
    }
 }
