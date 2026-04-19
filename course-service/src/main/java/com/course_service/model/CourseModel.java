package com.course_service.model;

import com.course_service.constants.enums.LEVEL;
import lombok.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Document(collection = "courses")
@Data
public class CourseModel {

    @Id
    private String id;   // MongoDB uses String/ObjectId

    private String title;

    private String description;

    private String category;

    private String instructorId;   // store as String (from user-service)

    private String thumbnailUrl;

    private Double price = 0.0;

    private Integer durationInMin;

    private Integer enrolledStudents ;

    private LEVEL level;

    private Set<String> users;

    // Embedded documents (no join needed!)
    private List<TutorialModel> tutorials = new ArrayList<>();


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt = LocalDateTime.now();
}
