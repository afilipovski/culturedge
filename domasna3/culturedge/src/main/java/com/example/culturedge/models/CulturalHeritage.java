package com.example.culturedge.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cultural_heritage")
public class CulturalHeritage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    public String name;
    public double lat;
    public double lon;
    public String historic;
    public String tourism;

    public CulturalHeritage() {
    }

    public CulturalHeritage(String name, double lat, double lon, String historic, String tourism) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.historic = historic;
        this.tourism = tourism;
    }

    @Override
    public String toString() {
        return "CulturalHeritage{" +
                "name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", historic='" + historic + '\'' +
                ", tourism='" + tourism + '\'' +
                '}';
    }
}
