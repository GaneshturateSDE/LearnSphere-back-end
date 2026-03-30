package com.User_service.DTO;

import com.User_service.constants.LEVEL;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseResponseDTO {
    private String id;

    private String title;

    private String description;

    private String category;

    private String instructorId;   // store as String (from user-service)

    private String thumbnailUrl;

    private Double price = 0.0;

    private Integer durationInHours;

    private LEVEL level;

    // Embedded documents (no join needed!)
//    private List<TutorialModel> tutorials = new ArrayList<>();
}
