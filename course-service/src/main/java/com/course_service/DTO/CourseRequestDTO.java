package com.course_service.DTO;

import com.course_service.constants.enums.LEVEL;
import com.course_service.model.TutorialModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


import java.util.ArrayList;
import java.util.List;

@Data
public class CourseRequestDTO {

    private String title;

    private String description;

    private String category;

    private String instructorId;   // store as String (from user-service)


    private String thumbnailUrl="http://localhost:8085/api/courses/files/images/course_thumbnail.png";

    private Double price ;

    private Integer durationInMin;

    private LEVEL level;

    // Embedded documents (no join needed!)
    private List<TutorialModel> tutorials = new ArrayList<>();

}
