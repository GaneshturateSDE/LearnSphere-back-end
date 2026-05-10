package com.payment_service.services;

import com.payment_service.DTO.PaymentRequestDTO;
import com.razorpay.RazorpayException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PaymentService {

    ResponseEntity<Map<String,Object>> createPayment(PaymentRequestDTO paymentRequestDTO) throws RazorpayException;
    ResponseEntity<Map<String,Object>> verifyPayment(Map<String,Object> payload);

}
