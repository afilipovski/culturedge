package com.example.culturedge.web;

import com.example.culturedge.models.CulturalHeritage;
import com.example.culturedge.service.HeritageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HeritageController {
    final HeritageService heritageService;

    public HeritageController(HeritageService heritageService) {
        this.heritageService = heritageService;
    }

    @GetMapping("/sites")
    public List<CulturalHeritage> getUsers() {
        return heritageService.getAll();
    }
}
