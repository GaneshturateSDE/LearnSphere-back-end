package com.User_service.repository;

import com.User_service.DTO.UserResponseDTO;
import com.User_service.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends MongoRepository<UserModel,String> {
    UserModel getUserByEmail(String email);
}
