package com.example.feedbackmicroservice.service;
// Interface that defines methods for sending emails.
public interface EmailService {
    void sendEmail(String email, String message);
}
