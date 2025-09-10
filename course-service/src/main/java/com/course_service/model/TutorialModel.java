package com.course_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutorials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content; // could also be a video link, markdown, etc.

    private String resourceUrl; // optional (pdf, ppt, external doc)

    @Column(nullable = false)
    private Integer orderIndex; // order inside the course

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseModel course;
}
