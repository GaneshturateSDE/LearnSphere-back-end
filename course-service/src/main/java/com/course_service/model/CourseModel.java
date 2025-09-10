package com.course_service.model;

import com.course_service.constants.enums.LEVEL;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String category;

    private Integer instructor_id;

    private String thumbnailUrl; // preview image

    private Double price = 0.0; // 0.0 means free

    @Column(nullable = false)
    private Integer durationInHours; // or minutes

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LEVEL level; // Beginner / Intermediate / Advanced

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TutorialModel> tutorials = new ArrayList<>();


    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

}
