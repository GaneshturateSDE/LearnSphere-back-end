package com.User_service.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
     private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;

    public boolean sendEmail(String to,String subject,String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try {
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // true = HTML
        helper.setFrom(email);
        mailSender.send(message);
         return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
