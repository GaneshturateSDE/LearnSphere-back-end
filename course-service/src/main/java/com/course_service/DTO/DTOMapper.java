package com.course_service.DTO;

import com.course_service.model.CourseModel;

public class DTOMapper {
    public static CourseResponseDTO mapToGetCourseDTO(CourseModel cm){
        CourseResponseDTO gc=new CourseResponseDTO();
        gc.setId(cm.getId());
        gc.setDescription(cm.getDescription());
        gc.setCategory(cm.getCategory());
        gc.setLevel(cm.getLevel());
        gc.setInstructorId(cm.getInstructorId());
//        gc.setDurationInHours(cm.getDurationInHours());
//        gc.setTitle(cm.getTitle());
        gc.setThumbnailUrl(cm.getThumbnailUrl());
        gc.setTutorials(cm.getTutorials());
        return gc;
    }

    public static  CourseModel mapToModel(CourseRequestDTO crd){
        CourseModel c=new CourseModel();
        c.setPrice(crd.getPrice());
        c.setLevel(crd.getLevel());
        c.setInstructorId(crd.getInstructorId());
        c.setTitle(crd.getTitle());
        c.setDescription(crd.getDescription());
        c.setThumbnailUrl(crd.getThumbnailUrl());
        c.setCategory(crd.getCategory());
//        c.setDurationInHours(crd.getDurationInHours());
        c.setTutorials(crd.getTutorials());

        return c;
    }

}
