package com.example.culturedge.repository;

import com.example.culturedge.models.CulturalHeritage;
import com.example.culturedge.models.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
    Description findByCulturalHeritage(CulturalHeritage culturalHeritage);
}
