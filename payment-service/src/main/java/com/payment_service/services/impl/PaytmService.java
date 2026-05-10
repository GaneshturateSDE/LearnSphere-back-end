package com.payment_service.services.impl;

import com.payment_service.DTO.PaymentRequestDTO;
import com.payment_service.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "PAYTM")
public class PaytmService implements PaymentService {
    @Override
    public ResponseEntity<Map<String, Object>> createPayment(PaymentRequestDTO paymentRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> verifyPayment(Map<String, Object> payload) {
        return null;
    }
}
