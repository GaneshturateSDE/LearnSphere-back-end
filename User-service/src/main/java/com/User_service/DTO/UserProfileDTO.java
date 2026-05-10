package com.User_service.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserProfileDTO {
    private String id;   // MongoDB uses String/ObjectId

    private String profileUrl;

    private String name;

    private String location;

    private String userType;   // STUDENT / INSTRUCTOR

    private String email;

    private List<String> coursesId=new ArrayList<>();
}
