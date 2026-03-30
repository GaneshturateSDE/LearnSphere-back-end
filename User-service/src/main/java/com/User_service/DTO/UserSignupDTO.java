package com.User_service.DTO;

import lombok.Data;

@Data
public class UserSignupDTO {

    String name;
    String email;
    String userType;
    String password;
}
