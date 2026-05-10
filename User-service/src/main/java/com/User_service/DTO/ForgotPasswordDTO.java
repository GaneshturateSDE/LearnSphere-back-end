package com.User_service.DTO;

import lombok.Data;

@Data
public class ForgotPasswordDTO {
    private String otp;
    private String password;
}
