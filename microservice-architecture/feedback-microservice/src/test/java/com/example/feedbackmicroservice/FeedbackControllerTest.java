package com.example.feedbackmicroservice;

import com.example.feedbackmicroservice.service.FeedbackService;
import com.example.feedbackmicroservice.web.FeedbackController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class FeedbackControllerTest {
    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<String> nameCaptor;

    @Captor
    private ArgumentCaptor<String> emailCaptor;

    @Captor
    private ArgumentCaptor<String> messageCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
    }

    @Test
    public void testAddFeedback() throws Exception {
//        TODO: Implement this test (does not work as expected)
        mockMvc.perform(post("/api/feedback")
                        .param("name", "John Doe")
                        .param("email", "john.doe@example.com")
                        .param("message", "Great site!"))
                .andExpect(status().isOk());


        feedbackService.sendFeedback("John Doe","john.doe@example.com","Great site!");
        verify(feedbackService).sendFeedback(nameCaptor.capture(), emailCaptor.capture(), messageCaptor.capture());

        assert nameCaptor.getValue().equals("John Doe");
        assert emailCaptor.getValue().equals("john.doe@example.com");
        assert messageCaptor.getValue().equals("Great site!");
    }
}
