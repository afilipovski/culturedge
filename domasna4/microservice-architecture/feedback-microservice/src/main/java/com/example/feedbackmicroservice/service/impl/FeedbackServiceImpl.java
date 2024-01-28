package com.example.feedbackmicroservice.service.impl;

import com.example.feedbackmicroservice.repository.FeedbackRepository;
import com.example.feedbackmicroservice.service.EmailService;
import com.example.feedbackmicroservice.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final EmailService emailService;

    public FeedbackServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }
    // Sends feedback email with provided name, email, and message.
    @Override
    public void sendFeedback(String name, String email, String message) {
        StringBuilder content = new StringBuilder();

        content.append("New feedback from\n");
        content.append("Name:   ").append(name).append("\n");
        content.append("Email: ").append(email).append("\n");
        content.append("Message: ").append(message);
        emailService.sendEmail(email, content.toString());
    }
}
