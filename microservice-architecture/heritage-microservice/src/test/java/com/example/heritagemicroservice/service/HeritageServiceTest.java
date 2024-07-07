package com.example.heritagemicroservice.service;


import com.example.heritagemicroservice.filters.BoundaryStonesRemoval;
import com.example.heritagemicroservice.filters.NullNamesRemovalFilter;
import com.example.heritagemicroservice.filters.TagRemovalFilter;
import com.example.heritagemicroservice.helpers.Node;
import com.example.heritagemicroservice.helpers.Tag;
import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.pipe.Pipe;
import com.example.heritagemicroservice.repository.HeritageRepository;
import com.example.heritagemicroservice.service.impl.HeritageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class HeritageServiceTest {

    @MockBean
    private HeritageRepository heritageRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private HeritageService heritageService;

    @BeforeEach
    public void setUp() {
        reset(heritageRepository);
    }

    @Test
    public void testGetAll() {
        List<CulturalHeritage> sites = new ArrayList<>();
        CulturalHeritage ch = new CulturalHeritage();
        ch.setName("Heritage site");
        sites.add(ch);

        when(heritageRepository.findAll()).thenReturn(sites);

        List<CulturalHeritage> result = heritageService.getAll();

        assertEquals(1, result.size());
        assertEquals("Heritage site", result.getFirst().getName());
    }

    @Test
    public void testInitialDataLoad() throws Exception {
        // Create test nodes
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

        // Mock the behavior of the pipeline
        Pipe<List<Node>, List<CulturalHeritage>> pipe =
                new Pipe<>(new TagRemovalFilter())
                        .chain(new NullNamesRemovalFilter())
                        .chain(new BoundaryStonesRemoval());

        List<CulturalHeritage> chList = pipe.process(nodes);

        assertEquals(1, chList.size());
        assertEquals("Heritage 2", chList.getFirst().getName());
        assertEquals(2.0, chList.getFirst().getLat());
        assertEquals(2.0, chList.getFirst().getLon());

        chList.forEach(culturalHeritage -> {
            HashMap<String, String> locationInfo = locationService.getLocationInfo(culturalHeritage.getLat(), culturalHeritage.getLon());
            culturalHeritage.setAddress(locationInfo.get("address"));
            culturalHeritage.setCity(locationInfo.get("city"));
        });

        heritageRepository.saveAll(chList);
        verify(heritageRepository, times(1)).saveAll(anyList());
    }
}
