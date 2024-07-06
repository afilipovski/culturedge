package com.example.feedbackmicroservice.repository;

import com.example.feedbackmicroservice.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
// Repository class for managing feedback data.
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
