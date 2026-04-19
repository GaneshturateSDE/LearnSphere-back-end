package com.User_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OtpService {
    @Autowired
    private SecureRandom random;

    public  String otpGenarate(){
        int otp = 100000 + random.nextInt(900000); // ensures 6-digit
        return String.valueOf(otp);
    }
}
