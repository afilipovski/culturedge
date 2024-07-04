package com.example.heritagemicroservice.filter;

import com.example.heritagemicroservice.filters.NullNamesRemovalFilter;
import com.example.heritagemicroservice.models.CulturalHeritage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullNamesRemovalFilterTest {

    private NullNamesRemovalFilter nullNamesRemovalFilter;

    @BeforeEach
    public void setUp() {
        nullNamesRemovalFilter = new NullNamesRemovalFilter();
    }

    @Test
    public void testExecute() {
        List<CulturalHeritage> sites = new ArrayList<>();
        CulturalHeritage site1 = new CulturalHeritage(null, 1.0, 1.0, "Historic", "Tourism", "Address 1", "City 1");
        CulturalHeritage site2 = new CulturalHeritage("Site 2", 2.0, 2.0, "Historic 2", "Tourism 2", "Address 2", "City 2");
        sites.add(site1);
        sites.add(site2);

        List<CulturalHeritage> result = nullNamesRemovalFilter.execute(sites);

        assertEquals(1, result.size());
        assertEquals("Site 2", result.getFirst().getName());

    }
}
