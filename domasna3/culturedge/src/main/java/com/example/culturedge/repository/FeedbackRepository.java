package com.example.culturedge.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class FeedbackRepository {
    HashMap<String, List<String>> inMemoryData = new HashMap<>();

    public List<String> getFeedbackByName(String name) {
        return inMemoryData.get(name);
    }
    public void addFeedbackByName(String name, String feedback) {
        inMemoryData.putIfAbsent(name,new ArrayList<>());
        inMemoryData.get(name).add(feedback);
    }
}
