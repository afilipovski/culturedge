package com.example.culturedge.service.impl;

import com.example.culturedge.repository.DescriptionRepository;
import com.example.culturedge.service.DescriptionService;
import org.springframework.stereotype.Service;

@Service
public class DescriptionServiceImpl implements DescriptionService {
    private final DescriptionRepository descriptionRepository;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    @Override
    public String getByName(String name) {
        return descriptionRepository.getByName(name);
    }

    @Override
    public void setByName(String name, String desc) {
        descriptionRepository.setByName(name,desc);
    }
}
