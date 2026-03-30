package com.course_service.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.course_service.DTO.CourseRequestDTO;
import com.course_service.DTO.TutorialRequestDTO;
import com.course_service.model.CourseModel;
import com.course_service.services.CourseService;
import org.springframework.http.RequestEntity;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import com.server.model.Course;
//import com.server.model.User;
//import com.server.services.CourseService;
//import com.server.utility.CloudinaryUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService cs;

    @GetMapping("/health")
    public ResponseEntity<Map<String,Object>> getServer(){

        Map<String, Object> map = new HashMap<>();
        map.put("message","Welcome to courseServices");

        return ResponseEntity.ok(map);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getCourse(){
    return cs.getCourse();
    }

    @PostMapping
    public  ResponseEntity<Map<String,Object>> createCourse(@RequestBody  CourseRequestDTO cr){
        return cs.createCourse(cr);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getCourseById(@PathVariable String id){
        return cs.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteCourseById(@PathVariable String id){
        return cs.deleteCourseById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateCourseById(@PathVariable String id,@RequestBody  CourseRequestDTO crd){
        return cs.updateById(id,crd);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String,Object>> getCoursesByInstructorId(@PathVariable("id") String instructorId){
        return cs.getCoursesByInstructorId(instructorId);
    }

    @PostMapping("/{id}/tutorials/")
    public  ResponseEntity<Map<String,Object>> addTutorials(@RequestAttribute("tutorials") List<TutorialRequestDTO> tutorials,@PathVariable("id") String courseId){
        return cs.addTutorials(tutorials,courseId);
    }



}
