package com.course_service.DTO;

import com.course_service.constants.enums.LEVEL;
import com.course_service.model.TutorialModel;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class CourseRequestDTO {

    private String title;

    private String description;

    private String category;

    private String instructorId;   // store as String (from user-service)

    private String thumbnailUrl;

    private Double price ;

    private Integer durationInHours;

    private LEVEL level;

    // Embedded documents (no join needed!)
    private List<TutorialModel> tutorials = new ArrayList<>();

}
