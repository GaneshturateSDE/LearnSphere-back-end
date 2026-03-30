package com.User_service.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDTO {

    private String id;   // MongoDB uses String/ObjectId

    private String profileUrl;

    private String name;

    private String userType;   // STUDENT / INSTRUCTOR

    private String email;

    private List<String> coursesId;

    private LocalDateTime createdAt = LocalDateTime.now();


    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
