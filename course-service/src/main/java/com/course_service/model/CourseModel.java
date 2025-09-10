package com.course_service.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Courses")
@Data
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    private String title;

    private String description;

    private Integer price;


    @Override
    public String toString() {
        return "Course [_id=" + id + ", description=" + description + ", price=" + price
                + ", Author="
                 + "]";
    }

}