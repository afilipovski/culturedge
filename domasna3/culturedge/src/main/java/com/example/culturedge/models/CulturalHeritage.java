package com.example.culturedge.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cultural_heritage")
public class CulturalHeritage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long chId;

    public String name;
    public double lat;
    public double lon;
    public String historic;
    public String tourism;
    public String address;
    public String city;

    public CulturalHeritage(String name, double lat, double lon, String historic, String tourism, String address, String city) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.historic = historic;
        this.tourism = tourism;
        this.address = address;
        this.city = city;
    }

    @Override
    public String toString() {
        return "CulturalHeritage{" +
                "Id=" + chId +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", historic='" + historic + '\'' +
                ", tourism='" + tourism + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
