package com.User_service.repository;

import com.User_service.model.OtpModel;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends MongoRepository<OtpModel,String> {

    void deleteByEmail(String email);

    OtpModel getOtpModelByOtp(String otp);
}
