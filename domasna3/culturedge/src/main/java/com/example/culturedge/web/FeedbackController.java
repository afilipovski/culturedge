package com.example.culturedge.web;

import com.example.culturedge.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {
    final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedback")
    public List<String> getFeedback(@RequestParam String name) {
        List<String> res = feedbackService.getFeedbackByName(name);
        if (res == null)
            return new ArrayList<>();
        return res;
    }

    @PostMapping("/feedback")
    public void addFeedback(@RequestParam String name, @RequestParam String feedback) {
        feedbackService.addFeedbackByName(name,feedback);
    }
}
