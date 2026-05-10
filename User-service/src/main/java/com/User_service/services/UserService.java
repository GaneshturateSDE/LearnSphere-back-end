package com.User_service.services;

import com.User_service.DTO.*;
import com.User_service.model.UserModel;
import com.User_service.repository.UserRepo;
import com.User_service.utility.Utility;
import jakarta.mail.Multipart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {

   private final UserRepo ur;


    private final ModelMapper mm;

    @Value("${file.upload-dir}")
    private String DIR_NAME;


    private final PasswordEncoder passwordEncoder;

    private final CourseService courseService;

    UserService(UserRepo userRepo,ModelMapper modelMapper,PasswordEncoder passwordEncoder,CourseService courseService){
            ur=userRepo;
            this.passwordEncoder=passwordEncoder;
            this.courseService=courseService;
            mm=modelMapper;
    }

    public ResponseEntity<Map<String,Object>> getUsers(){
         List<UserProfileDTO> list=ur.findAll().stream().map(obj->mm.map(obj,UserProfileDTO.class)).toList();
         Map<String,Object>map=new HashMap<>();
         map.put("data",list);
         return ResponseEntity.ok(map);
    }



    public ResponseEntity<Map<String,Object>>  getUser(String id){
        System.out.println("user id: "+id);
        UserProfileDTO upd=mm.map(ur.getUserById(id),UserProfileDTO.class);

        Map<String,Object> map=new HashMap<>();
        map.put("user",upd);
        return  ResponseEntity.status(200).body(map);
    }

    public ResponseEntity<Map<String,Object>> updateUser(String id,UserUpdateDTO user){
         UserModel us=ur.getUserById(id);

            us.setName(user.getName());
            us.setLocation(user.getLocation());

        System.out.println(us.toString());
         UserProfileDTO ud=mm.map( ur.save(us),UserProfileDTO.class);

           return  ResponseEntity.ok(Map.of("user",ud,"message","Updated successfully"));
    }

    public ResponseEntity<Map<String,Object>> deleteUser(String id){
        ur.deleteById(id);
        return  ResponseEntity.ok(Map.of("message","Deleted successfully"));
            }

    public ResponseEntity<Map<String,Object>> forgotPassword(String email){
         return ResponseEntity.status(200).body(Map.of("message","Forgot Password successfully"));
    }

    public ResponseEntity<Map<String,Object>> updateProfileImage(String id,MultipartFile file) throws IOException {
         String profileUrl= Utility.getImageUrl(DIR_NAME,file);

         UserModel um=ur.getUserById(id);
                 um.setName(um.getName());
          um.setProfileUrl(profileUrl);

        um=ur.save(um);

       return  ResponseEntity.ok(Map.of("message","profile image updated","user",um));
    }

    public ResponseEntity<Map<String,Object>> updatePassword(String id, UpdatePasswordDTO password){
               UserModel um=ur.getUserById(id);
               if(passwordEncoder.matches(password.getOldPassword(),um.getPassword())){
                   um.setPassword(passwordEncoder.encode(password.getNewPassword()));
                   return ResponseEntity.ok(Map.of("message","Updated successfully"));
               }

               return ResponseEntity.status(400).body(Map.of("message","Invalid password"));
    }

    @Transactional()
    public ResponseEntity<Map<String,Object>> enrollCourse(String userId,String courseId){
        System.out.println("userid:-"+userId+" courseid:-"+courseId);
             UserModel um=ur.getUserById(userId);
               Set<String> courseids=um.getCoursesId();
              if(courseids==null)
                   courseids=new HashSet<>();

             if( !courseids.contains(courseId)){
                  courseids.add(courseId);
                  ur.save(um);

                 ResponseEntity<Map<String,Object>> response=courseService.enrollCourse(courseId,userId);
                 if(response.getStatusCode()== HttpStatusCode.valueOf(201)){
                       return  ResponseEntity.status(200).body(Map.of("message","Enrolled"));

             }
              }

          return ResponseEntity.status(400).body(Map.of("message","enrollment error"));
    }


}
