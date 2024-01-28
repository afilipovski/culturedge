package com.example.heritagemicroservice.repository;

import com.example.heritagemicroservice.models.CulturalHeritage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for managing cultural heritage data.
@Repository
public interface HeritageRepository extends JpaRepository<CulturalHeritage, Long> {
    // Method to find a cultural heritage by its name.
    CulturalHeritage findByName(String name);
}
