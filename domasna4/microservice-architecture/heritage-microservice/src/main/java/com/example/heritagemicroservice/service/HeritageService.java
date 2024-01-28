package com.example.heritagemicroservice.service;

import com.example.heritagemicroservice.models.CulturalHeritage;

import java.util.List;
// Interface that defines methods for managing cultural heritage sites.
public interface HeritageService {

    // Method to retrieve all cultural heritage sites.
    List<CulturalHeritage> getAll();
}
