package com.User_service.model;

import lombok.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import java.util.Set;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    private String id;   // MongoDB uses String/ObjectId


    private String profileUrl;

    private String name;

    private String userType;   // STUDENT / INSTRUCTOR

    private String email;

    private String password;   // store hashed password only

    private Boolean active=false;

    private Set<String> coursesId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

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
