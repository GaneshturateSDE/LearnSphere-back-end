package com.course_service.services;

import com.course_service.DTO.*;
import com.course_service.constants.enums.LEVEL;
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
import java.util.*;
import java.util.stream.Collectors;

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

    public ResponseEntity<Map<String, Object>> getCourse(String search,
                                                         String categories,
                                                         String levels,
                                                         Double minPrice,
                                                         Double maxPrice,
                                                         Integer limit,
                                                         Integer page) {

        List<CourseModel> courses = cr.findAll();

        // Apply filters
        if (search != null && !search.isEmpty()) {
            courses = courses.stream()
                    .filter(c -> c.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (categories != null && !categories.isEmpty()) {
            Set<String> categorySet = new HashSet<>(Arrays.asList(categories.split(",")));
            courses = courses.stream()
                    .filter(c -> categorySet.contains(c.getCategory()))
                    .collect(Collectors.toList());
        }

        if (levels != null && !levels.isEmpty()) {
            Set<String> levelSet = new HashSet<>(Arrays.asList(levels.split(",")));
            courses = courses.stream()
                    .filter(c -> levelSet.contains(c.getLevel().toString()))
                    .collect(Collectors.toList());
        }

        if (minPrice != null && maxPrice != null) {
            courses = courses.stream()
                    .filter(c -> c.getPrice() >= minPrice && c.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }

        // ✅ Pagination logic
        int totalItems = courses.size();

        int currentPage = (page != null && page > 0) ? page : 1;
        int pageSize = (limit != null && limit > 0) ? limit : 10;

        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalItems);

        List<CourseModel> paginatedCourses = new ArrayList<>();

        if (fromIndex < totalItems) {
            paginatedCourses = courses.subList(fromIndex, toIndex);
        }

        // Convert to DTO
        List<CourseResponseDTO> dtoList = paginatedCourses.stream()
                .map(c -> modelMapper.map(c, CourseResponseDTO.class))
                .toList();

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Success");
        map.put("data", dtoList);
        map.put("currentPage", currentPage);
        map.put("totalItems", totalItems);
        map.put("totalPages", totalPages);

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
        System.out.println(cc.toString());
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


    public ResponseEntity<Map<String,Object>> enrollCourse(String courseId,String userId){
        System.out.println("course_Id:-"+courseId+" UserId:-"+userId);
        CourseModel cm=cr.getById(courseId);
        Set<String> users=cm.getUsers();
           if(users==null)
                 users=new HashSet<>();

          users.add(userId);
        cm.setUsers(users);
        cr.save(cm);

        return  ResponseEntity.status(201).body(Map.of("status","success"));

    }


    public ResponseEntity<Map<String,Object>> getCoursesByUser(String coursesIds){
        String [] courses=coursesIds.split(",");
        List<CourseResponseDTO> courseResponseDTOS= Arrays.stream(courses).map(c->modelMapper.map(cr.getById(c),CourseResponseDTO.class)).toList();

        if(courseResponseDTOS.isEmpty()) return  ResponseEntity.ok(Map.of("message","courses not found"));


        return ResponseEntity.ok(Map.of("data",courseResponseDTOS));
    }



}
