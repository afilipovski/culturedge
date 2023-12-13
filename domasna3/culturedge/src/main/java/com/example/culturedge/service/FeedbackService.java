package com.example.culturedge.service;

import java.util.List;

public interface FeedbackService {
    List<String> getFeedbackByName(String name);
    void addFeedbackByName(String name, String feedback);
}
