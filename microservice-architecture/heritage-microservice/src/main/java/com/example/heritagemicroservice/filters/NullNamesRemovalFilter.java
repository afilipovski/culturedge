package com.example.heritagemicroservice.filters;


import com.example.heritagemicroservice.models.CulturalHeritage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The NullNamesRemovalFilter is a concrete filter that removes items with null names.
 */
public class NullNamesRemovalFilter implements Filter<List<CulturalHeritage>, List<CulturalHeritage>> {

    /**
     * Executes the filter logic to remove items with null names.
     *
     * @param input The list of CulturalHeritage items to be filtered.
     * @return The filtered list of CulturalHeritage items.
     */
    @Override
    public List<CulturalHeritage> execute(List<CulturalHeritage> input) {
        return input.stream().filter(culturalHeritage -> culturalHeritage.name != null).collect(Collectors.toList());
    }
}
