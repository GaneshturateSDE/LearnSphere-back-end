package com.course_service.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    @PostConstruct
    public void init() {
        System.out.println("WebConfig bean initialized");
        System.out.println("Uploads folder absolute path: " + Paths.get("uploads").toAbsolutePath());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadsPath = Paths.get("course-service/uploads/").toAbsolutePath().toUri().toString();
        System.out.println("Serving files from: " + uploadsPath);

        // This URL pattern must match what the client requests
        registry.addResourceHandler("/api/courses/files/**") // include /api if your gateway exposes /api
                .addResourceLocations(uploadsPath) // must be file: URI
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
