package com.example.culturedge.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PhotoRepository {
    //kluch: ime na lokalitet
    //vrednost: url na slika
    HashMap<String, List<String>> inMemoryData;

    public PhotoRepository() {
        this.inMemoryData = new HashMap<>();
    }

    public List<String> getByName(String name) {
        if (!inMemoryData.containsKey(name))
            return new ArrayList<>();
        return inMemoryData.get(name);
    }
    public List<String> addByName(String name, String url) {
        if (inMemoryData.containsKey(name))
            inMemoryData.get(name).add(url);
        else {
            inMemoryData.put(name, new ArrayList<>());
            inMemoryData.get(name).add(url);
        }
        return getByName(name);
    }
    public List<String> deleteByName(String name, String url) {
        if (!inMemoryData.containsKey(name))
            return getByName(name);
        inMemoryData.put(name, inMemoryData.get(name)
                .stream().filter(urli -> !urli.equals(url))
                .collect(Collectors.toList()));
        return getByName(name);
    }
}
