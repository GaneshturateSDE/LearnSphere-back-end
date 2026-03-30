package com.User_service.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserRequestDTO {


    private String profileUrl;

    private String name;

    private String userType;   // STUDENT / INSTRUCTOR

    private String email;

    private String password;   // store hashed password only

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "UserModel{" +
                 '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
