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


}
