package com.example.heritagemicroservice.repository;

import com.example.heritagemicroservice.models.CulturalHeritage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class HeritageRepositoryTest {
    @Autowired
    private HeritageRepository heritageRepository;

    @Test
    public void testFindByName() {
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

        CulturalHeritage found = heritageRepository.findByName("Heritage name");
        assertNotNull(found);
        assertEquals("Heritage name", found.getName());
    }
}
