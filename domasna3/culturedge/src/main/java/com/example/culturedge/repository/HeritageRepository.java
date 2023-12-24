package com.example.culturedge.repository;

import com.example.culturedge.models.CulturalHeritage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeritageRepository extends JpaRepository<CulturalHeritage, Long> {
    CulturalHeritage findByName(String name);
}
