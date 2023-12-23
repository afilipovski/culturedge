package com.example.culturedge.models;

import jakarta.persistence.*;

@Entity
public class Description {
    @EmbeddedId
    public DescriptionId id;
    @MapsId("chId")
    @OneToOne
    @JoinColumn(name = "ch_id")
    public CulturalHeritage culturalHeritage;

    @Column(length = 5000)
    public String description;

    public Description() {
    }

    public Description(CulturalHeritage culturalHeritage, String description) {
        this.culturalHeritage = culturalHeritage;
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
