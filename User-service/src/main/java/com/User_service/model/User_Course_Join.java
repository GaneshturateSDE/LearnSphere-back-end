package com.User_service.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "User_Course_Join")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User_Course_Join {



    // Foreign key to User
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // Foreign key to Course (from course service → just store courseId)
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(nullable = false)
    private Double progress = 0.0; // 0.0 to 100.0 %

    @Column(name = "subscribed_at", updatable = false)
    private LocalDateTime subscribedAt = LocalDateTime.now();

    @Column(name = "status")
    private String status;

    @Column(name = "added_by")
    private String addedBy; // e.g. "self", "admin"

    @Override
    public String toString() {
        return "Enrollment{" +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", progress=" + progress +
                ", subscribedAt=" + subscribedAt +
                ", status=" + status +
                ", addedBy='" + addedBy + '\'' +
                '}';
    }
}
