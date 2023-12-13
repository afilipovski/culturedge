package com.example.culturedge.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DescriptionRepository {
    HashMap<String,String> descriptionByName = new HashMap<>();

    public String getByName(String name) {
        return descriptionByName.get(name);
    }
    public void setByName(String name, String desc) {
        descriptionByName.put(name,desc);
    }
}
