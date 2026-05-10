package com.payment_service.controller;

import com.payment_service.DTO.PaymentRequestDTO;
import com.payment_service.services.PaymentService;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {


    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<Map<String,Object >> getServer(){
        Map<String,Object> map=new HashMap<>();
        map.put("message","Welcome to Payment");

        return ResponseEntity.ok(map);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) throws RazorpayException {
        return  paymentService.createPayment(paymentRequestDTO);
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String,Object>> verifyPayment(@RequestBody Map<String,Object> payload){
        return paymentService.verifyPayment(payload);
    }
}
