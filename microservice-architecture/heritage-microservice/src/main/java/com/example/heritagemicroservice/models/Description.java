package com.example.heritagemicroservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

// Entity class representing the description of a cultural heritage site.
@Entity
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    public CulturalHeritage culturalHeritage;

    @Column(length = 5000)
    public String description;

    public Description() {
    }

    public Description(CulturalHeritage culturalHeritage, String description) {
        this.culturalHeritage = culturalHeritage;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Description{" +
                "culturalHeritage=" + culturalHeritage +
                ", description='" + description + '\'' +
                '}';
    }
}
