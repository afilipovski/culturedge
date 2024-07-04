package com.example.heritagemicroservice.service;

import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.models.Description;
import com.example.heritagemicroservice.repository.DescriptionRepository;
import com.example.heritagemicroservice.repository.HeritageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class DescriptionServiceTest {

    @Autowired
    private DescriptionService descriptionService;

    @MockBean
    private DescriptionRepository descriptionRepository;

    @MockBean
    private HeritageRepository heritageRepository;

    @BeforeEach
    public void setUp() {
        reset(descriptionRepository);
        reset(heritageRepository);
    }

    @Test
    public void testGetByName_DescriptionExists() {
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

        when(heritageRepository.findByName("Heritage name")).thenReturn(heritage);
        when(descriptionRepository.findByCulturalHeritage(heritage)).thenReturn(description);

        String desc = descriptionService.getByName("Heritage name");
        assertEquals("Test description", desc);
    }

    @Test
    public void testGetByName_NoDescription() {
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

        when(heritageRepository.findByName("Heritage name")).thenReturn(heritage);
        when(descriptionRepository.findByCulturalHeritage(heritage)).thenReturn(null);

        String desc = descriptionService.getByName("Heritage name");
        assertEquals("No description yet.", desc);
    }

    @Test
    public void testSetByName_DescriptionExists() {
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
        Description existingDescription = new Description(heritage, "Old description");

        when(heritageRepository.findByName("Heritage name")).thenReturn(heritage);
        when(descriptionRepository.findByCulturalHeritage(heritage)).thenReturn(existingDescription);

        descriptionService.setByName("Heritage name", "New description");

        verify(descriptionRepository, times(1)).save(existingDescription);
        assertEquals("New description", existingDescription.getDescription());
    }

    @Test
    public void testSetByName_NoExistingDescription() {
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

        when(heritageRepository.findByName("Heritage Name")).thenReturn(heritage);

        descriptionService.setByName("Heritage Name", "New Description");

        verify(descriptionRepository, times(1)).save(any(Description.class));
    }
}
