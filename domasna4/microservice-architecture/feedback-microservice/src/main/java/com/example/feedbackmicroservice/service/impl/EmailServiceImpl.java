package com.example.feedbackmicroservice.service.impl;

import com.example.feedbackmicroservice.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String email, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("culturedge24@gmail.com");
        mailMessage.setTo("culturedge24@gmail.com");
        mailMessage.setSubject(String.format("Feedback from: %s",email));
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
}