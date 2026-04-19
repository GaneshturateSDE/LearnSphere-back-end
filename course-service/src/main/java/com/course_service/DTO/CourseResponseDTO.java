package com.course_service.DTO;

import com.course_service.constants.enums.LEVEL;
import com.course_service.model.TutorialModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseResponseDTO {
    private String id;

    private String title;

    private String description;

    private String category;

    private String instructorId;   // store as String (from user-service)

     @Value("${thumbnail-url}")
    private String thumbnailUrl;

    private Double price = 0.0;

    private Integer durationInMin;

    private LEVEL level;

    // Embedded documents (no join needed!)
    private List<TutorialModel> tutorials = new ArrayList<>();
}
