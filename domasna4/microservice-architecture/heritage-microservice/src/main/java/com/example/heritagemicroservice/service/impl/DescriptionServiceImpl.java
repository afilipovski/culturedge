package com.example.heritagemicroservice.service.impl;

import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.models.Description;
import com.example.heritagemicroservice.repository.DescriptionRepository;
import com.example.heritagemicroservice.repository.HeritageRepository;
import com.example.heritagemicroservice.service.DescriptionService;
import org.springframework.stereotype.Service;

// Implementation of DescriptionService interface for managing descriptions of cultural heritage sites.
@Service
public class DescriptionServiceImpl implements DescriptionService {
    private final DescriptionRepository descriptionRepository;
    private final HeritageRepository heritageRepository;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository, HeritageRepository heritageRepository) {
        this.descriptionRepository = descriptionRepository;
        this.heritageRepository = heritageRepository;
    }

    // Method to retrieve the description of a cultural heritage site by its name.
    @Override
    public String getByName(String name) {
        CulturalHeritage culturalHeritage = this.heritageRepository.findByName(name);
        if (descriptionRepository.findByCulturalHeritage(culturalHeritage) != null) {
            return descriptionRepository.findByCulturalHeritage(culturalHeritage).description;
        } else {
            return "No description yet.";
        }
    }

    // Method to set the description of a cultural heritage site by its name.
    @Override
    public void setByName(String name, String desc) {
        CulturalHeritage culturalHeritage = this.heritageRepository.findByName(name);
        Description description = descriptionRepository.findByCulturalHeritage(culturalHeritage);
        description.setDescription(desc);
        descriptionRepository.save(description);
    }
}
