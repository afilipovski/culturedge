package com.example.heritagemicroservice.models;

import jakarta.persistence.*;

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
