package com.example.culturedge.service.impl;

import com.example.culturedge.repository.FeedbackRepository;
import com.example.culturedge.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<String> getAll() {
        return feedbackRepository.getAll();
    }

    @Override
    public void addFeedbackByName(String feedback) {
        feedbackRepository.addFeedback(feedback);
    }
}
