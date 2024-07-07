package com.example.heritagemicroservice.controller;

import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.repository.DescriptionRepository;
import com.example.heritagemicroservice.repository.HeritageRepository;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class HeritageControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private DescriptionRepository descriptionRepository;

    @Autowired
    private HeritageRepository heritageRepository;

    @BeforeEach
    public void setUp() {
        descriptionRepository.deleteAll();
        heritageRepository.deleteAll();
    }

    @Test
    public void testGetAllSites() throws Exception {
        CulturalHeritage site1 = new CulturalHeritage("Site 1", 1.0, 1.0, "Historic", "Tourism", "Address 1", "City 1");
        CulturalHeritage site2 = new CulturalHeritage("Site 2", 2.0, 2.0, "Historic 2", "Tourism 2", "Address 2", "City 2");

        heritageRepository.save(site1);
        heritageRepository.save(site2);

        JSONArray expectedJson = new JSONArray();
        expectedJson.add(createJsonWithoutId(site1));
        expectedJson.add(createJsonWithoutId(site2));

        mockMvc.perform(get("/api/sites")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson.toJSONString(), false));
    }

    private JSONObject createJsonWithoutId(CulturalHeritage site) {
        JSONObject json = new JSONObject();
        json.put("name", site.getName());
        json.put("lat", site.getLat());
        json.put("lon", site.getLon());
        json.put("historic", site.getHistoric());
        json.put("tourism", site.getTourism());
        json.put("address", site.getAddress());
        json.put("city", site.getCity());
        return json;
    }
}
