package com.User_service.controller;
import java.util.HashMap;
import java.util.Map;

import com.User_service.DTO.UpdatePasswordDTO;
import com.User_service.DTO.UserLoginDTO;
import com.User_service.DTO.UserUpdateDTO;
import com.User_service.model.UserModel;
import com.User_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import com.server.model.User;
//import com.server.services.UserService;
//
//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    UserService us;


    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsers() {
        return us.getUsers();
    }

    @GetMapping("/self")
    public ResponseEntity<Map<String, Object>> getUser(@RequestHeader("X-User-Id") String id) {
        return us.getUser(id);
    }

    @PutMapping("/self")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestHeader("X-User-Id") String id,@RequestBody UserUpdateDTO user) {
      return   us.updateUser(id,user);
    }

    @DeleteMapping("/self")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestHeader("X-User-Id") String id) {
        return us.deleteUser(id);
    }

    @PutMapping("/update-password")
    public ResponseEntity<Map<String,Object>> updatePassword(@RequestHeader("X-User-Id") String id,@RequestBody UpdatePasswordDTO user) {
        return  us.updatePassword(id,user);
    }

    @PutMapping("/forgot-password")
    public  ResponseEntity<Map<String,Object>> forgotPassword(@RequestBody String  email) {
        return  us.forgotPassword(email);
    }

    @PutMapping("/enroll-course/{cid}")
    public  ResponseEntity<Map<String,Object>> enrollCourse(@PathVariable String  cid) {

        return  ResponseEntity.ok(Map.of("cid",cid));
    }

}
