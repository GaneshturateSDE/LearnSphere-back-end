package com.User_service.repository;

import com.User_service.DTO.UserProfileDTO;
import com.User_service.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserModel,String> {
   UserModel getUserById(String id);


}
