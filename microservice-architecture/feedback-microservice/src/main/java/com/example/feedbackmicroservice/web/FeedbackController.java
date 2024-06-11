package com.example.feedbackmicroservice.web;

import com.example.feedbackmicroservice.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
// Controller class for handling feedback-related HTTP requests.
public class FeedbackController {
    final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedback")
    public void addFeedback(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String message) {
        feedbackService.sendFeedback(name, email, message);
    }
}
