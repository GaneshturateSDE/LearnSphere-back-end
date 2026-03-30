package com.course_service.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public  CourseNotFoundException(String message){
        super(message);
    }
}
