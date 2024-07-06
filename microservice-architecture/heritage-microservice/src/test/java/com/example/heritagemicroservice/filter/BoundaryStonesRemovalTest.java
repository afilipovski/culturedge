package com.example.heritagemicroservice.filter;

import com.example.heritagemicroservice.filters.BoundaryStonesRemoval;
import com.example.heritagemicroservice.models.CulturalHeritage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoundaryStonesRemovalTest {

    private BoundaryStonesRemoval boundaryStonesRemoval;

    @BeforeEach
    public void setUp() {
        boundaryStonesRemoval = new BoundaryStonesRemoval();
    }

    @Test
    public void testExecute() {
        List<CulturalHeritage> sites = new ArrayList<>();
        sites.add(new CulturalHeritage("Heritage 1", 1.0, 1.0, "boundary_stone", "Tourism", "Address", "City"));
        sites.add(new CulturalHeritage("Heritage 2", 2.0, 2.0, "Historic", "Tourism", "Address", "City"));

        List<CulturalHeritage> result = boundaryStonesRemoval.execute(sites);

        assertEquals(1, result.size());
        assertEquals("Heritage 2", result.getFirst().getName());
    }
}
