package com.example.culturedge.service.impl;

import com.example.culturedge.models.CulturalHeritage;
import com.example.culturedge.repository.HeritageRepository;
import com.example.culturedge.service.HeritageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeritageServiceImpl implements HeritageService {
    HeritageRepository heritageRepository;

    public HeritageServiceImpl(HeritageRepository heritageRepository) {
        this.heritageRepository = heritageRepository;
    }

    @Override
    public List<CulturalHeritage> getAll() {
        return heritageRepository.getAll();
    }
}
