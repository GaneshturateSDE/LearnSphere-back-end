package com.User_service.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("course-service")
public interface CourseService {



}
