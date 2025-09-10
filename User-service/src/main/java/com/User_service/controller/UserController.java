package com.User_service.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.server.model.User;
//import com.server.services.UserService;
//
//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsers() {

        Map<String, Object> map = new HashMap<>();
        map.put("message","Welcome to userService");

        return ResponseEntity.ok(map);
    }

    @GetMapping("/course")
    public ResponseEntity<Map<String, Object>> getUsersCourse() {

        Map<String, Object> map = new HashMap<>();
        map.put("message","Welcome to Course");

        return ResponseEntity.ok(map);
    }

//    @Autowired
//    UserService userService;
//
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getUsers() {
//
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("data", userService.getUsers());
//        return ResponseEntity.ok(map);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable("id") Integer id) {
//
//        Map<String, Object> map = new HashMap<>();
//        User user = userService.getUserById(id);
//        if (user != null)
//            map.put("data", user);
//        else
//            map.put("message", "User Not Found");
//        return ResponseEntity.ok(map);
//    }
//
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody User user, BindingResult result) {
//        Map<String, Object> map = new HashMap<>();
//
//        if (result.hasErrors()) {
//            Map<String, Object> errors = new HashMap<>();
//
//            result.getFieldErrors().forEach(error -> {
//                errors.put(error.getField(), error.getDefaultMessage());
//            });
//
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        System.out.println(user.toString());
//        user = userService.createUser(user);
//        if (user != null) {
//            map.put("message", "SignUp Successfull ");
//            map.put("data", user);
//        } else {
//            map.put("message", "signup failed ");
//        }
//
//        return ResponseEntity.ok(map);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("id") Integer id) {
//        Map<String, Object> map = new HashMap<>();
//
//        try {
//            userService.deleteUserById(id);
//            map.put("message", "User deleted ");
//
//        } catch (Exception e) {
//            map.put("message", "User Not Created ");
//
//        }
//
//        return ResponseEntity.ok(map);
//    }

}
