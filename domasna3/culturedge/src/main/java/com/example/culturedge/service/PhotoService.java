package com.example.culturedge.service;

import java.util.List;

public interface PhotoService {
    List<String> getByName(String name);

    List<String> addByName(String name, String url);

    List<String> deleteByName(String name, String url);
}
