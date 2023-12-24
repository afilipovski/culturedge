package com.example.culturedge.service.impl;

import com.example.culturedge.models.CulturalHeritage;
import com.example.culturedge.models.Description;
import com.example.culturedge.repository.DescriptionRepository;
import com.example.culturedge.repository.HeritageRepository;
import com.example.culturedge.service.DescriptionService;
import org.springframework.stereotype.Service;

@Service
public class DescriptionServiceImpl implements DescriptionService {
    private final DescriptionRepository descriptionRepository;
    private final HeritageRepository heritageRepository;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository, HeritageRepository heritageRepository) {
        this.descriptionRepository = descriptionRepository;
        this.heritageRepository = heritageRepository;
    }

    @Override
    public String getByName(String name) {
        CulturalHeritage culturalHeritage = this.heritageRepository.findByName(name);
        if (descriptionRepository.findByCulturalHeritage(culturalHeritage) != null) {
            return descriptionRepository.findByCulturalHeritage(culturalHeritage).description;
        } else {
            return "";
        }
    }

    @Override
    public void setByName(String name, String desc) {
        CulturalHeritage culturalHeritage = this.heritageRepository.findByName(name);
        descriptionRepository.save(new Description(culturalHeritage, desc));
    }
}
