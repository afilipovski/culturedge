package com.example.heritagemicroservice.controller;

import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.models.Description;
import com.example.heritagemicroservice.repository.DescriptionRepository;
import com.example.heritagemicroservice.repository.HeritageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DescriptionRepository descriptionRepository;
    @Autowired
    private HeritageRepository heritageRepository;

    @BeforeEach
    public void setUp() {
        descriptionRepository.deleteAll();
        heritageRepository.deleteAll();
    }

    @Test
    public void testGetDescription() throws Exception {
        CulturalHeritage heritage = new CulturalHeritage(
                "Heritage name",
                1.0,
                1.0,
                "Historic",
                "Tourism",
                "Address",
                "City"
        );
        heritage.chId = 1L;
        Description description = new Description(heritage, "Test description");

        heritageRepository.save(heritage);
        descriptionRepository.save(description);

        mockMvc.perform(get("/api/description")
                        .param("name", "Heritage name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Test description"));
    }

    @Test
    public void testSetDescription() throws Exception {
        CulturalHeritage heritage = new CulturalHeritage(
                "Heritage name",
                1.0,
                1.0,
                "Historic",
                "Tourism",
                "Address",
                "City"
        );
        heritage.chId = 1L;
        heritageRepository.save(heritage);

        mockMvc.perform(post("/api/description")
                        .param("name", "Heritage name")
                        .param("desc", "New Description")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("New Description"));
    }
}
