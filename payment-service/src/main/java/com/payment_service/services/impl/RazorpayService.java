package com.payment_service.services.impl;

import com.payment_service.DTO.PaymentRequestDTO;
import com.payment_service.constant.GateWay;
import com.payment_service.constant.Status;
import com.payment_service.model.Payment;
import com.payment_service.repository.PaymentRepository;
import com.payment_service.services.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Primary
@Service(value = "RAZORPAY")
@RequiredArgsConstructor
public class RazorpayService implements PaymentService {

    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<Map<String, Object>> createPayment(PaymentRequestDTO paymentRequestDTO) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount",paymentRequestDTO.getAmount()*100);
        options.put("currency",paymentRequestDTO.getCurrency());
        options.put("receipt","txn_"+paymentRequestDTO.getCreatedAt());

        Order order=razorpayClient.orders.create(options);
        Payment payment=Payment.builder().orderId(order.get("id")).gateWay(GateWay.RAZORPAY).amount(paymentRequestDTO.getAmount()).status(Status.CREATED).paymentMethod(paymentRequestDTO.getPaymentMethod()).userId(paymentRequestDTO.getUserId()).courseId(paymentRequestDTO.getCourseId()).createdAt(paymentRequestDTO.getCreatedAt()).build();
      paymentRepository.save(payment);
      return  ResponseEntity.status(201).body(Map.of("orderId", order.get("id"),"amount", order.get("amount"),"currency", order.get("currency")));
    }

    @Override
    public ResponseEntity<Map<String, Object>> verifyPayment(Map<String, Object> payload) {
        String razorpayOrderId = payload.get("razorpay_order_id").toString();
        String paymentId = payload.get("razorpay_payment_id").toString();

        Payment payment = paymentRepository
                .findByOrderId(razorpayOrderId)
                .orElseThrow();

        payment.setPaymentId(paymentId);
        payment.setStatus(Status.SUCCESS);

        paymentRepository.save(payment);
    return ResponseEntity.ok(Map.of("message","Payment Success"));
    }
}
