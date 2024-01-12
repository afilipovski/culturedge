package com.example.culturedge.service;

import java.util.List;

public interface FeedbackService {
    List<String> getAll();

    void sendFeedback(String name, String email, String message);
}
