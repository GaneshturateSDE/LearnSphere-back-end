package com.course_service.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="course-progress")
    @Data
public class CourseProgressModel {

        String CourseId;
        String TutorialId;
        Integer userId;
        Boolean completed;


}
