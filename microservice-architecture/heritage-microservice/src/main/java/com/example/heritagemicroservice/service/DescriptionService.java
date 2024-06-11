package com.example.heritagemicroservice.service;

// Interface that defines methods for managing descriptions of cultural heritage sites.
public interface DescriptionService {

    // Method to retrieve the description of a cultural heritage site by its name.
    String getByName(String name);

    // Method to set the description of a cultural heritage site by its name.
    void setByName(String name, String desc);
}
