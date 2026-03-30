package com.course_service.repository;

import com.course_service.DTO.CourseRequestDTO;
import com.course_service.DTO.CourseResponseDTO;
import com.course_service.model.CourseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends MongoRepository<CourseModel,String> {
     CourseModel getById(String id);
      void deleteById(String id);

     List<CourseResponseDTO> getCourseByInstructorId(String instructorId);

}
