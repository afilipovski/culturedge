package com.example.heritagemicroservice.filters;

import com.example.heritagemicroservice.helpers.Node;
import com.example.heritagemicroservice.models.CulturalHeritage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The TagRemovalFilter is a concrete filter that transforms a list of Node objects into a list of CulturalHeritage objects,
 * removing specific tags and mapping the properties to CulturalHeritage fields.
 */
public class TagRemovalFilter implements Filter<List<Node>, List<CulturalHeritage>> {

    /**
     * Executes the filter logic to transform a list of Node objects into a list of CulturalHeritage objects,
     * removing specific tags and mapping the properties to CulturalHeritage fields.
     *
     * @param input The list of Node objects to be transformed.
     * @return The transformed list of CulturalHeritage objects.
     */
    @Override
    public List<CulturalHeritage> execute(List<Node> input) {
        return input.stream()
                .map(node -> new CulturalHeritage(
                        node.getProperty("name"),
                        node.lat,
                        node.lon,
                        node.getProperty("historic"),
                        node.getProperty("tourism"), "", "")).collect(Collectors.toList());

    }

}
