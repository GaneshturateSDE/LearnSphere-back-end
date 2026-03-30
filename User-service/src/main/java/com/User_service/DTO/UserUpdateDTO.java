package com.User_service.DTO;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String profileUrl;

    private String name;

    private String userType;   // STUDENT / INSTRUCTOR

    private String email;

    private String password;   // store hashed password only
}
