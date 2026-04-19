package com.course_service.services;

import com.course_service.DTO.CourseRequestDTO;
import com.course_service.DTO.DTOMapper;
import com.course_service.DTO.CourseResponseDTO;
import com.course_service.DTO.TutorialRequestDTO;
import com.course_service.exceptions.CourseNotFoundException;
import com.course_service.model.CourseModel;
import com.course_service.model.TutorialModel;
import com.course_service.repository.CourseRepo;
import com.course_service.repository.TutorialRepo;
import com.course_service.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    TutorialRepo tr;

    @Autowired
    private ModelMapper modelMapper ;

    @Value("${file.upload-dir}")
    private String dir_name;

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

    public ResponseEntity<Map<String,Object>> createCourse(CourseRequestDTO cc){

        CourseModel cm=modelMapper.map(cc, CourseModel.class);
           System.out.println(cm.toString());
            cr.save(cm);
            Map<String,Object> map=new HashMap<>();
            map.put("message","Created");
        return ResponseEntity.ok(map);
    }

    public ResponseEntity<Map<String,Object>> deleteCourseById(String id){
         CourseResponseDTO cm=modelMapper.map(cr.getById(id),CourseResponseDTO.class);
         if(cm==null)
             throw new CourseNotFoundException("Course Not Found");

        cr.deleteById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("message","Deleted");
        return ResponseEntity.status(201).body(map);
    }

    public ResponseEntity<Map<String,Object>> updateById(String id,CourseRequestDTO crd, MultipartFile mf) throws IOException {
        System.out.println("Updated");
        String fileUrl="";
          if(mf!=null)
            fileUrl= Utility.getImageUrl(dir_name,mf);
    CourseModel oldData=cr.getById(id);
        System.out.println("Saved file at: " + fileUrl);
//        System.out.println("file name"+mf.getOriginalFilename());
        CourseModel cm=modelMapper.map(crd, CourseModel.class);
        cm.setId(id);
        cm.setTutorials(oldData.getTutorials());
        cm.setThumbnailUrl(oldData.getThumbnailUrl());
        if(!fileUrl.isEmpty())
         cm.setThumbnailUrl(fileUrl);

        CourseModel updateData=cr.save(cm);
        Map<String,Object> map=new HashMap<>();
        map.put("data",modelMapper.map(updateData, CourseResponseDTO.class));
        map.put("message","Data Updated");
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

    public ResponseEntity<Map<String,Object>> addTutorials(TutorialRequestDTO tutorial,MultipartFile file,String courseId) throws IOException {
         CourseModel course=cr.getById(courseId);
         List<TutorialModel> list=course.getTutorials();
        String fileUrl="";
        if(file!=null)
           fileUrl= Utility.getImageUrl(dir_name,file);

        if(!fileUrl.isEmpty())
            tutorial.setResourceUrl(fileUrl);

        list.add(tr.save(modelMapper.map(tutorial,TutorialModel.class)));
        course.setTutorials(list);
           cr.save(course);

         return ResponseEntity.ok(Map.of("message","Tutorial Added Succesfull"));
    }


}
