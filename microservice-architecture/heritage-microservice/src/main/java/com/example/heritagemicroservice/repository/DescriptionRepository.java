package com.example.heritagemicroservice.repository;

import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.models.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Repository interface for managing descriptions of cultural heritage sites.
@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
    // Method to find a description by its associated cultural heritage.
    Description findByCulturalHeritage(CulturalHeritage culturalHeritage);
}
