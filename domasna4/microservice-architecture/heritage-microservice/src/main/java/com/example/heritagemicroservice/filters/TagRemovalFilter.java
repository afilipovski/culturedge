package com.example.heritagemicroservice.filters;

import com.example.heritagemicroservice.helpers.Node;
import com.example.heritagemicroservice.models.CulturalHeritage;

import java.util.List;
import java.util.stream.Collectors;

public class TagRemovalFilter implements Filter<List<Node>, List<CulturalHeritage>> {

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
