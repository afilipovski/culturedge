package com.example.heritagemicroservice.repository;

import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.models.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
    Description findByCulturalHeritage(CulturalHeritage culturalHeritage);
}
