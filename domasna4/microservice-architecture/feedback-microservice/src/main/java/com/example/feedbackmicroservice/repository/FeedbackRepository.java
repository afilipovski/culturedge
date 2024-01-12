package com.example.feedbackmicroservice.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FeedbackRepository {
    List<String> inMemoryData = new ArrayList<>();

    public List<String> getAll() {
        return inMemoryData;
    }

    public void addFeedback(String feedback) {
        inMemoryData.add(feedback);
    }
}
