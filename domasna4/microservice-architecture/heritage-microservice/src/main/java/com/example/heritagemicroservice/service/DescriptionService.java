package com.example.heritagemicroservice.service;

public interface DescriptionService {
    String getByName(String name);
    void setByName(String name, String desc);
}
