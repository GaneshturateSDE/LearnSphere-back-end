package com.User_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class UtilConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }

    @Bean
    public SecureRandom getSecureRandom(){
        return new SecureRandom();
    }
}
