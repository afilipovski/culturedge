package com.example.feedbackmicroservice.service;

import java.util.List;

// Interface that defines methods for managing feedback submissions.
public interface FeedbackService {
    void sendFeedback(String name, String email, String message);
}
