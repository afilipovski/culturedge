package com.example.heritagemicroservice.filters;


import com.example.heritagemicroservice.models.CulturalHeritage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The BoundaryStonesRemoval is a concrete filter that removes items with historic type "boundary_stone".
 */
public class BoundaryStonesRemoval implements Filter<List<CulturalHeritage>, List<CulturalHeritage>> {

    /**
     * Executes the filter logic to remove items with historic type "boundary_stone".
     *
     * @param input The list of CulturalHeritage items to be filtered.
     * @return The filtered list of CulturalHeritage items.
     */
    @Override
    public List<CulturalHeritage> execute(List<CulturalHeritage> input) {
        return input.stream().filter(culturalHeritage -> culturalHeritage.historic != null &&
                        !culturalHeritage.historic.equals("boundary_stone"))
                .collect(Collectors.toList());
    }
}
