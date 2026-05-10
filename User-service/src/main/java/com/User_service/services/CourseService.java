package com.User_service.services;

import com.User_service.DTO.EnrollCourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Map;

@FeignClient(name="course-service")
public interface CourseService {


    @PostMapping("/api/courses/enroll/{courseId}/{userId}")
     ResponseEntity<Map<String,Object>> enrollCourse(@PathVariable String courseId,@PathVariable String userId);


}
