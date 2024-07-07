package com.example.heritagemicroservice.filter;

import com.example.heritagemicroservice.filters.BoundaryStonesRemoval;
import com.example.heritagemicroservice.filters.NullNamesRemovalFilter;
import com.example.heritagemicroservice.filters.TagRemovalFilter;
import com.example.heritagemicroservice.helpers.Node;
import com.example.heritagemicroservice.helpers.Tag;
import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.pipe.Pipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PipelineTest {

    private Pipe<List<Node>, List<CulturalHeritage>> pipeline;

    @BeforeEach
    public void setUp() {
        pipeline = new Pipe<>(new TagRemovalFilter())
                .chain(new NullNamesRemovalFilter())
                .chain(new BoundaryStonesRemoval());
    }

    @Test
    public void testPipeline() {
        List<Node> nodes = new ArrayList<>();

        // Node with a null name
        Node node1 = new Node();
        node1.lat = 1.0;
        node1.lon = 1.0;
        node1.tags = new ArrayList<>();

        Tag nullNameTag = new Tag();
        nullNameTag.k = "name";
        nullNameTag.v = null;
        node1.tags.add(nullNameTag);

        nodes.add(node1);

        // Node with a valid name and no boundary stone tag
        Node node2 = new Node();
        node2.lat = 2.0;
        node2.lon = 2.0;
        node2.tags = new ArrayList<>();

        Tag regularTag = new Tag();
        regularTag.k = "name";
        regularTag.v = "Heritage 2";
        Tag historicTag = new Tag();
        historicTag.k = "historic";
        historicTag.v = "monument";
        node2.tags.add(regularTag);
        node2.tags.add(historicTag);

        nodes.add(node2);

        // Node with a boundary stone tag
        Node node3 = new Node();
        node3.lat = 3.0;
        node3.lon = 3.0;
        node3.tags = new ArrayList<>();
        Tag boundaryTag = new Tag();
        boundaryTag.k = "name";
        boundaryTag.v = "Boundary stone";
        Tag stoneTag = new Tag();
        stoneTag.k = "historic";
        stoneTag.v = "boundary_stone";
        node3.tags.add(boundaryTag);
        node3.tags.add(stoneTag);
        nodes.add(node3);

        List<CulturalHeritage> result = pipeline.process(nodes);

        assertEquals(1, result.size());
        assertEquals("Heritage 2", result.getFirst().getName());
        assertEquals(2.0, result.getFirst().getLat());
        assertEquals(2.0, result.getFirst().getLon());
    }
}
