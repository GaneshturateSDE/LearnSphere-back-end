package com.course_service.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {



    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
