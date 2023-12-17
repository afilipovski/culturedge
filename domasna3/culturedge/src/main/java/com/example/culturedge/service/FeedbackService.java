package com.example.culturedge.service;

import java.util.List;

public interface FeedbackService {
    List<String> getAll();

    void addFeedbackByName(String feedback);
}
