package com.example.heritagemicroservice.service;

import com.example.heritagemicroservice.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class LocationServiceTest {

    @InjectMocks
    private LocationServiceImpl locationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        locationService = new LocationServiceImpl(new RestTemplate());
    }

    @Test
    public void testPlaceholder() {
        // Placeholder test that always passes
        assertTrue(true, "NEED TO IMPLEMENT");
    }
}
