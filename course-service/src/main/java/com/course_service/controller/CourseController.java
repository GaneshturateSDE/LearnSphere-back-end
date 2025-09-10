package com.course_service.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.server.model.Course;
//import com.server.model.User;
//import com.server.services.CourseService;
//import com.server.utility.CloudinaryUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/courses")
public class CourseController {


    @GetMapping
    public ResponseEntity<Map<String,Object>> getServer(){

        Map<String, Object> map = new HashMap<>();
        map.put("message","Welcome to courseServices");

        return ResponseEntity.ok(map);
    }

//    @Autowired
//    CourseService cs;
//
//    @Autowired
//    CloudinaryUtil cu;
//
//    @GetMapping
//    public List<Course> getCourses() {
//        return cs.getCourses();
//    }
//
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> createCourse(@RequestParam("title") String title,
//                                                            @RequestParam("description") String description, @RequestParam("user_id") Integer user_id,
//                                                            @RequestParam("image") MultipartFile imageFile) {
//
//        Course course = new Course();
//        try {
//            course.setImage(cu.uploadImage(imageFile));
//        } catch (Exception e) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("message", "Something error");
//            return ResponseEntity.badRequest().body(map);
//        }
//        User user = new User();
//        user.setId(user_id);
//        course.setAuthor(user);
//        course.setTitle(title);
//        course.setDescription(description);
//        System.out.println("id--->" + course.getAuthor().getId());
//
//        return cs.createCourse(course);
//    }

}
