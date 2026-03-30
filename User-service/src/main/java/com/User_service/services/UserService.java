package com.User_service.services;

import com.User_service.DTO.*;
import com.User_service.model.UserModel;
import com.User_service.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
   private UserRepo ur;

    @Autowired
    private ModelMapper mm;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
           us=mm.map(us,UserModel.class);
           ur.save(us);

           return  ResponseEntity.ok(Map.of("message","Updated successfully"));
    }

    public ResponseEntity<Map<String,Object>> deleteUser(String id){
        ur.deleteById(id);
        return  ResponseEntity.ok(Map.of("message","Deleted successfully"));
            }

    public ResponseEntity<Map<String,Object>> forgotPassword(String email){
         return ResponseEntity.status(200).body(Map.of("message","Forgot Password successfully"));
    }

    public ResponseEntity<Map<String,Object>> updatePassword(String id, UpdatePasswordDTO password){
               UserModel um=ur.getUserById(id);
               if(passwordEncoder.matches(password.getOldPassword(),um.getPassword())){
                   um.setPassword(passwordEncoder.encode(password.getNewPassword()));
                   return ResponseEntity.ok(Map.of("message","Updated successfully"));
               }

               return ResponseEntity.status(400).body(Map.of("message","Invalid password"));
    }

    public ResponseEntity<Map<String,Object>> enrollCourse(String id,String cid){
             UserModel um=ur.getUserById(id);
             if(um.getCoursesId().stream().noneMatch(cid::equals)){
                  um.getCoursesId().add(cid);
                  ur.save(um);

                  return  ResponseEntity.status(200).body(Map.of("message","Enrollment successfully"));

             }
          return ResponseEntity.status(400).body(Map.of("message","enrollment error"));
    }

}
