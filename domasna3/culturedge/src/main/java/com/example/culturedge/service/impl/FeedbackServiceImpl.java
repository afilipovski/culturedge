package com.example.culturedge.service.impl;

import com.example.culturedge.repository.FeedbackRepository;
import com.example.culturedge.service.EmailService;
import com.example.culturedge.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    FeedbackRepository feedbackRepository;
    private final EmailService emailService;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, EmailService emailService) {
        this.feedbackRepository = feedbackRepository;
        this.emailService = emailService;
    }

    @Override
    public List<String> getAll() {
        return feedbackRepository.getAll();
    }

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
