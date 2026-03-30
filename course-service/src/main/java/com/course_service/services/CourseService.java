package com.course_service.services;

import com.course_service.DTO.CourseRequestDTO;
import com.course_service.DTO.DTOMapper;
import com.course_service.DTO.CourseResponseDTO;
import com.course_service.DTO.TutorialRequestDTO;
import com.course_service.exceptions.CourseNotFoundException;
import com.course_service.model.CourseModel;
import com.course_service.model.TutorialModel;
import com.course_service.repository.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    CourseRepo cr;

    @Autowired
    private ModelMapper modelMapper ;

    public ResponseEntity<Map<String, Object>> getCourse(){
        List<CourseModel> list= cr.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("message","Success ");

     List<CourseResponseDTO> gclist=list.stream().map(c->modelMapper.map(c,CourseResponseDTO.class)).toList();

//        List<GetCourseDTO> gclist=new ArrayList<>();
//        for(CourseModel dm:list){
//            gclist.add(DTOMapper.mapToGetCourseDTO(dm));
//        }

        map.put("data",gclist);

        return ResponseEntity.ok(map);
    }


    public ResponseEntity<Map<String,Object>> getCourseById(String id){

        CourseResponseDTO cm=modelMapper.map(cr.getById(id),CourseResponseDTO.class);
        if(cm==null)
            throw new CourseNotFoundException("course of id :"+id+" not Found");

        Map<String, Object> map = new HashMap<>();
        map.put("message","Data of  "+id);
        map.put("data",cm);

        return  ResponseEntity.ok(map);
    }

    public ResponseEntity<Map<String,Object>> createCourse(  CourseRequestDTO cc){
        CourseModel cm=DTOMapper.mapToModel(cc);
           System.out.println(cc.toString());
            cr.save(cm);
            Map<String,Object> map=new HashMap<>();
            map.put("message","Course Created");
        return ResponseEntity.ok(map);
    }

    public ResponseEntity<Map<String,Object>> deleteCourseById(String id){
         CourseResponseDTO cm=modelMapper.map(cr.getById(id),CourseResponseDTO.class);
         if(cm==null)
             throw new CourseNotFoundException("Course Not Found");

        cr.deleteById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("message","Deleted Success");
        return ResponseEntity.status(201).body(map);
    }

    public ResponseEntity<Map<String,Object>> updateById(String id,CourseRequestDTO crd){
        CourseResponseDTO crd1=modelMapper.map(cr.getById(id),CourseResponseDTO.class);
        CourseModel cm=DTOMapper.mapToModel(crd);
        cm.setId(crd1.getId());
        CourseModel updateData=cr.save(cm);
        Map<String,Object> map=new HashMap<>();
        map.put("data",DTOMapper.mapToGetCourseDTO(updateData));
        map.put("message","Data Updated Succesfull");
        return  ResponseEntity.ok(map);
    }

    public ResponseEntity<Map<String,Object>> getCoursesByInstructorId(String instructorId){
        List<CourseResponseDTO> courses=cr.getCourseByInstructorId(instructorId);
        return ResponseEntity.ok(Map.of("data",courses));
    }

    public ResponseEntity<Map<String,Object>> getCoursesByIds(List<String> courseIds){
         List<CourseResponseDTO> courses=new ArrayList<>();
         for(String courseId:courseIds){
             courses.add(modelMapper.map(cr.getById(courseId),CourseResponseDTO.class));
         }
         return ResponseEntity.ok(Map.of("data",courses));
    }

    public ResponseEntity<Map<String,Object>> addTutorials(List<TutorialRequestDTO> tutorials,String courseId){
         CourseModel course=cr.getById(courseId);
         List<TutorialModel> list=course.getTutorials();
         for(TutorialRequestDTO t:tutorials){
             list.add(modelMapper.map(t,TutorialModel.class));
         }

         return ResponseEntity.ok(Map.of("message","Tutorial Added Succesfull"));
    }


}
