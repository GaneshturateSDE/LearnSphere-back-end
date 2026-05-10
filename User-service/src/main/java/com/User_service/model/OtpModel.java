package com.User_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.Instant;

@Document
@Data
public class OtpModel {
    @Id
    private String id;

    private String email;
    private String otp;

    @Indexed(expireAfter = "0s") // TTL index
    private Instant expiryTime=Instant.now().plusSeconds(300);
}
