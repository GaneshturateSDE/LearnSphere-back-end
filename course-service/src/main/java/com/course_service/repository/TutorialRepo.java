package com.course_service.repository;

import com.course_service.model.TutorialModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepo extends MongoRepository<TutorialModel,String> {
}
