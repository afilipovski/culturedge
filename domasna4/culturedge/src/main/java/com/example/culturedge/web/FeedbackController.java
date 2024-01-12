package com.example.culturedge.web;

import com.example.culturedge.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedback")
    public List<String> getFeedback() {
        return feedbackService.getAll();
    }

    @PostMapping("/feedback")
    public void addFeedback(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String message) {
        feedbackService.sendFeedback(name, email, message);
    }
}
