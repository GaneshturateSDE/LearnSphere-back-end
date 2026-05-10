package com.User_service.services;

import com.User_service.DTO.*;
import com.User_service.model.OtpModel;
import com.User_service.model.UserModel;
import com.User_service.repository.AuthRepo;
import com.User_service.repository.OtpRepo;
import com.User_service.utility.JwtUtil;
import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {


    private AuthRepo ar;

    private PasswordEncoder pe;

    private ModelMapper mm;

    private EmailService emailService;

    private OtpService otpService;

    private OtpRepo otpRepo;

    AuthService(AuthRepo ar, PasswordEncoder pe, ModelMapper mm, EmailService emailService, OtpRepo otpRepo, OtpService otpService) {
        this.ar = ar;
        this.pe = pe;
        this.mm = mm;
        this.emailService = emailService;
        this.otpRepo = otpRepo;
        this.otpService = otpService;
    }

    public ResponseEntity<Map<String, Object>> login(UserLoginDTO uld) {
        UserModel um = ar.getUserByEmail(uld.getEmail());
        if (um == null || !um.getActive())
            return ResponseEntity.status(400).body(Map.of("message", "Account Not Exist"));

        Map<String, Object> map = new HashMap<>();
        if (pe.matches(uld.getPassword(), um.getPassword())) {
            String token = JwtUtil.generateToken(um.getId(), um.getEmail());
            UserProfileDTO upd = mm.map(um, UserProfileDTO.class);
            map.put("token", token);
            map.put("user", upd);
            map.put("message", "Login Success!");
        } else {
            map.put("message", "Invalid Credentials");
            return ResponseEntity.badRequest().body(map);
        }

        return ResponseEntity.ok(map);
    }

    public ResponseEntity<Map<String, Object>> signup(UserSignupDTO usd) throws MessagingException {
        UserModel um = ar.getUserByEmail(usd.getEmail());
        if (um != null && um.getActive()) ResponseEntity.status(200).body(Map.of("message", "Account already exist"));

        String otp = otpService.otpGenarate();
        OtpModel otpModel = new OtpModel();
        otpModel.setOtp(otp);
        otpModel.setEmail(usd.getEmail());
        otpRepo.save(otpModel);
        Map<String, Object> map = new HashMap<>();
        if (emailService.sendEmail(usd.getEmail(), "Verify Email", "One time password is : " + otp + "\n Otp expires in 5 min"))
            map.put("otp", true);

        if (um == null) {
            um = mm.map(usd, UserModel.class);
            um.setPassword(pe.encode(usd.getPassword()));
            ar.save(um);
        }
        map.put("message", "Otp sent");

        return ResponseEntity.ok(map);
    }

    public ResponseEntity<Map<String, Object>> verifySignup(String otp) {
        OtpModel otpModel = otpRepo.getOtpModelByOtp(otp);
        if (otpModel == null) return ResponseEntity.ok(Map.of("message", "Otp expired"));

        System.out.println("opt" + otp + " " + otpModel.getOtp());
        if (otp.equals(otpModel.getOtp())) {
            UserModel userModel = ar.getUserByEmail(otpModel.getEmail());
            userModel.setActive(true);
            ar.save(userModel);
        } else {
            return ResponseEntity.status(400).body(Map.of("message", "Invalid"));
        }

        return ResponseEntity.ok(Map.of("message", "Signup Success"));
    }


    public ResponseEntity<Map<String, Object>> changePassword(String id, ChangePasswordDTO cpd) {
        System.out.println(cpd.toString());
        UserModel um = ar.findById(id).orElse(null);
        if (um == null) return ResponseEntity.status(403).body(Map.of("message", "Bad Request"));

        if (pe.matches(cpd.getCurrentPassword(), um.getPassword())) {
            um.setPassword(pe.encode(cpd.getNewPassword()));
            ar.save(um);
            return ResponseEntity.ok(Map.of("message", "Password Changed"));
        }

        return ResponseEntity.status(403).body(Map.of("message", "Invalid Current Password"));

    }

    public ResponseEntity<Map<String, Object>> forgotPassword(UserLoginDTO uld) throws MessagingException {

        UserModel um = ar.getUserByEmail(uld.getEmail());
        if (um == null) return ResponseEntity.status(403).body(Map.of("message", "Bad request"));

        String otp = otpService.otpGenarate();
        OtpModel otpModel = new OtpModel();
        otpModel.setOtp(otp);
        otpModel.setEmail(um.getEmail());
        otpRepo.save(otpModel);
        Map<String, Object> map = new HashMap<>();
        if (emailService.sendEmail(um.getEmail(), "Verify Email", "One time password is : " + otp + "\n Otp expires in 5 min"))
            map.put("otp", true);

        um = mm.map(um, UserModel.class);
        um.setPassword(pe.encode(um.getPassword()));
        ar.save(um);

        map.put("message", "Otp sent");

        return ResponseEntity.ok(map);


    }

    public ResponseEntity<Map<String, Object>> verifyForgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        String otp = forgotPasswordDTO.getOtp();
        OtpModel otpModel = otpRepo.getOtpModelByOtp(otp);
        if (otpModel == null) return ResponseEntity.ok(Map.of("message", "Otp expired"));
        System.out.println("opt" + otp + " " + otpModel.getOtp());
        if (otp.equals(otpModel.getOtp())) {
            UserModel userModel = ar.getUserByEmail(otpModel.getEmail());
            userModel.setPassword(pe.encode(forgotPasswordDTO.getPassword()));
            ar.save(userModel);
        } else {
            return ResponseEntity.status(400).body(Map.of("message", "Invalid"));
        }

        return ResponseEntity.ok(Map.of("message", "Password Updated success"));
    }

    private Boolean verifyOtp(String otp) {
        OtpModel otpModel = otpRepo.getOtpModelByOtp(otp);
        if (otpModel == null) return false;

        System.out.println("opt" + otp + " " + otpModel.getOtp());
        if (otp.equals(otpModel.getOtp()))
            return true;


        return false;
    }

    public ResponseEntity<Map<String, Object>> loginWithGoogle(UserRequestDTO userRequestDTO) {
        UserModel um = ar.getUserByEmail(userRequestDTO.getEmail());
        if (um == null)
            return ResponseEntity.status(400).body(Map.of("message", "Account Not exist", "isExist", false));

        String token = JwtUtil.generateToken(um.getId(), um.getEmail());
        if (token == null) return ResponseEntity.status(500).body(Map.of("message", "Try after sometime"));

        UserResponseDTO userResponseDTO=mm.map(um,UserResponseDTO.class);

        return ResponseEntity.ok(Map.of("token", token, "message", "Logging as " + um.getEmail(),"user",userResponseDTO));
    }

    public  ResponseEntity<Map<String,Object>> signupWithGoogle(UserRequestDTO userRequestDTO){
        System.out.println(userRequestDTO.toString());
        UserModel um=mm.map(userRequestDTO,UserModel.class);
        um.setActive(true);
        um=ar.save(um);
        UserResponseDTO userResponseDTO=mm.map(um,UserResponseDTO.class);
       return ResponseEntity.ok(Map.of("message","Logging as "+um.getEmail(),"user",userResponseDTO));

    }


}