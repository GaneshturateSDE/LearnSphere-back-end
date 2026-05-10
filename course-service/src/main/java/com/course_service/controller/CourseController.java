package com.course_service.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.course_service.DTO.CourseRequestDTO;

import com.course_service.DTO.TutorialRequestDTO;
import com.course_service.services.CourseService;
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
    public ResponseEntity<Map<String,Object>> getCourse(@RequestParam(required = false) String search,
                                                        @RequestParam(required = false) String categories,
                                                        @RequestParam(required = false) String levels,
                                                        @RequestParam(required = false) Double minPrice,
                                                        @RequestParam(required = false) Double maxPrice,
                                                        @RequestParam(required = false) Integer limit,
                                                        @RequestParam(required = false) Integer page
    ){
    return cs.getCourse(search, categories, levels, minPrice, maxPrice,limit,page);
    }

    @PostMapping
    public  ResponseEntity<Map<String,Object>> createCourse(@RequestBody  CourseRequestDTO cr){
        return cs.createCourse(cr);
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String,Object>> getCoursesByUser(@RequestParam(required = false) String coursesIds){
        System.out.println("coursesIds:-"+coursesIds);
        return cs.getCoursesByUser(coursesIds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getCourseById(@PathVariable String id){
        return cs.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteCourseById(@PathVariable String id){
        return cs.deleteCourseById(id);
    }

    @PutMapping(value = "/{id}",consumes ="multipart/form-data")
    public ResponseEntity<Map<String,Object>> updateCourseById(@PathVariable String id,@ModelAttribute  CourseRequestDTO crd,@RequestParam(value = "file",required = false) MultipartFile mf) throws IOException {
        return cs.updateById(id,crd,mf);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String,Object>> getCoursesByInstructorId(@PathVariable("id") String instructorId){
        return cs.getCoursesByInstructorId(instructorId);
    }

    @PostMapping(value = "/{id}/tutorials" ,consumes = "multipart/form-data")
    public  ResponseEntity<Map<String,Object>> addTutorials(@ModelAttribute TutorialRequestDTO tutorials,@RequestParam(value = "file",required = false) MultipartFile file,@PathVariable("id") String courseId) throws IOException {
        return cs.addTutorials(tutorials,file,courseId);
    }

    @PostMapping("/enroll/{courseId}/{userId}")
    public  ResponseEntity<Map<String,Object>> enrollCourses(@PathVariable String courseId, @PathVariable String userId){
        return cs.enrollCourse(courseId,userId);
    }





}
