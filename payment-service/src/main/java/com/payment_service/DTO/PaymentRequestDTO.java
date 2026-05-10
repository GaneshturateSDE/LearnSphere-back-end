package com.payment_service.DTO;

import com.payment_service.constant.PaymentMethod;
import com.payment_service.constant.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PaymentRequestDTO {
    private String userId;
    private List<String> courseId;
    private String currency;
    private PaymentMethod paymentMethod;
    private Double amount;
    private Status status;
    private LocalDateTime createdAt;
}
