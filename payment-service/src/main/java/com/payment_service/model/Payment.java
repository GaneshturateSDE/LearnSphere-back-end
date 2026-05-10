package com.payment_service.model;

import com.payment_service.constant.GateWay;
import com.payment_service.constant.PaymentMethod;
import com.payment_service.constant.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private String orderId;
    private String paymentId;
    private String userId;
    private List<String> courseId;
    private PaymentMethod paymentMethod;
    private String currency;
    private Double amount;
    private Status status;
    private GateWay gateWay;
    private LocalDateTime createdAt;
}
