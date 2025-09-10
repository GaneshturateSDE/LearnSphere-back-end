package com.User_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String profileUrl;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "user_type", nullable = false, length = 50)
    private String userType;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;  // ⚠ Store only hashed password

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();



    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", profileUrl='" + profileUrl + '\'' +
                ", name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
