package com.example.heritagemicroservice.filter;

import com.example.heritagemicroservice.filters.TagRemovalFilter;
import com.example.heritagemicroservice.helpers.Node;
import com.example.heritagemicroservice.models.CulturalHeritage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagRemovalFilterTest {

    private TagRemovalFilter tagRemovalFilter;

    @BeforeEach
    public void setUp() {
        tagRemovalFilter = new TagRemovalFilter();
    }

    @Test
    public void testExecute() {
        List<Node> nodes = new ArrayList<>();
        Node node1 = new Node();
        node1.lat = 1.0;
        node1.lon = 1.0;
        nodes.add(node1);

        Node node2 = new Node();
        node2.lat = 2.0;
        node2.lon = 2.0;
        nodes.add(node2);

        List<CulturalHeritage> result = tagRemovalFilter.execute(nodes);

        assertEquals(2, result.size());
        assertEquals(1.0, result.get(0).getLat());
        assertEquals(1.0, result.get(0).getLon());
        assertEquals(2.0, result.get(1).getLat());
        assertEquals(2.0, result.get(1).getLon());
    }
}
