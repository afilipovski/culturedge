package com.example.culturedge.models;


import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;

@Embeddable
public class DescriptionId implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long descriptionId;
    Long chId;
}
