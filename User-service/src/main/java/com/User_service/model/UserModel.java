package com.User_service.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "Users")
@Data
public class UserModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Value("")
    private String profileUrl;

    @NotBlank
    private String name;

    @Column(name = "userType", nullable = false)
    private String userType ;

    @Column(unique = true, nullable = false)
    private String email;

    private String Password;

    private List<Integer> courses = new ArrayList<>();



    @Override
    public String toString() {
        return "User [_id=" + id + ", profileUrl=" + profileUrl + ", name=" + name + ", email=" + email + ", Password="
                + Password + "]";
    }

}

