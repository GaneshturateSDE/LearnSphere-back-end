package com.payment_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @GetMapping
    public ResponseEntity<Map<String,Object >> getServer(){
        Map<String,Object> map=new HashMap<>();
        map.put("message","Welcome to Payment");

        return ResponseEntity.ok(map);
    }
}
