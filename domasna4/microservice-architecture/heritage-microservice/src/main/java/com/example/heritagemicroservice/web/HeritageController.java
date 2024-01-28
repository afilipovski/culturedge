package com.example.heritagemicroservice.web;

import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.service.HeritageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
// This class handles HTTP requests related to cultural heritage sites.
public class HeritageController {
    final HeritageService heritageService;

    public HeritageController(HeritageService heritageService) {
        this.heritageService = heritageService;
    }

    // Retrieves all cultural heritage sites.
    @GetMapping("/sites")
    public List<CulturalHeritage> getUsers() {
        return heritageService.getAll();
    }
}
