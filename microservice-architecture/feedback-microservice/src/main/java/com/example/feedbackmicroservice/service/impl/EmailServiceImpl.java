package com.example.feedbackmicroservice.service.impl;

import com.example.feedbackmicroservice.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        System.out.println("hello world");
    }

    // Sends an email with the provided email address and message content.
    public void sendEmail(String email, String message) {

    }
}