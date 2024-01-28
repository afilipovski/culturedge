package com.example.heritagemicroservice.filters;


import com.example.heritagemicroservice.models.CulturalHeritage;

import java.util.List;
import java.util.stream.Collectors;

// Filter implementation to remove cultural heritage sites with null names from the list.
public class NullNamesRemovalFilter implements Filter<List<CulturalHeritage>, List<CulturalHeritage>> {
    @Override
    public List<CulturalHeritage> execute(List<CulturalHeritage> input) {
        return input.stream().filter(culturalHeritage -> culturalHeritage.name != null).collect(Collectors.toList());
    }
}
