package com.example.heritagemicroservice.service;

import com.example.heritagemicroservice.models.CulturalHeritage;

import java.util.List;

public interface HeritageService {
    List<CulturalHeritage> getAll();
}
