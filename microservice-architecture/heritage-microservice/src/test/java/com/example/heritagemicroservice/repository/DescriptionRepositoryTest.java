package com.example.heritagemicroservice.repository;

import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.models.Description;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class DescriptionRepositoryTest {

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Autowired
    private HeritageRepository heritageRepository;

    @Test
    public void testFindByCulturalHeritage() {
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

        Description description = new Description(heritage, "Test description");
        descriptionRepository.save(description);

        Description found = descriptionRepository.findByCulturalHeritage(heritage);
        assertNotNull(found);
        assertEquals("Test description", found.getDescription());
    }

}
