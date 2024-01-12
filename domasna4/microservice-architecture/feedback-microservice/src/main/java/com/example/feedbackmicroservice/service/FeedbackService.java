package com.example.feedbackmicroservice.service;

import java.util.List;

public interface FeedbackService {
    void sendFeedback(String name, String email, String message);
}
