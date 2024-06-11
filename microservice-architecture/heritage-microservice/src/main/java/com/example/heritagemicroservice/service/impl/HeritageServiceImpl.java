package com.example.heritagemicroservice.service.impl;

import com.example.heritagemicroservice.filters.BoundaryStonesRemoval;
import com.example.heritagemicroservice.filters.NullNamesRemovalFilter;
import com.example.heritagemicroservice.filters.TagRemovalFilter;
import com.example.heritagemicroservice.helpers.DConfig;
import com.example.heritagemicroservice.helpers.Node;
import com.example.heritagemicroservice.helpers.OsmModel;
import com.example.heritagemicroservice.models.CulturalHeritage;
import com.example.heritagemicroservice.pipe.Pipe;
import com.example.heritagemicroservice.repository.HeritageRepository;
import com.example.heritagemicroservice.service.HeritageService;
import com.example.heritagemicroservice.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

// Implementation of HeritageService interface for managing cultural heritage sites.
@Service
public class HeritageServiceImpl implements HeritageService {
    HeritageRepository heritageRepository;
    LocationService locationService;

    // Constructor with dependency injection.
    public HeritageServiceImpl(HeritageRepository heritageRepository, LocationService locationService) throws IOException, InterruptedException {
        this.heritageRepository = heritageRepository;
        this.locationService = locationService;

        // If no cultural heritage data exists in the repository, load data from external source.
        if (heritageRepository.findAll().size() == 0) {
            File outputFile = new File("data/output.osm");
            if (!outputFile.exists()) {
                DConfig dConfig;
                ObjectMapper objectMapper = new ObjectMapper();
                try (InputStream fileStream = new FileInputStream("config.json")) {
                    dConfig = objectMapper.readValue(fileStream, DConfig.class);
                }
                // Execute converter and filter commands.
                // First, create a process builder for the converter command specified in the configuration.
                ProcessBuilder converter = new ProcessBuilder(dConfig.converterCommand.split(" "));
                Process c = converter.start();

                // Next, create a process builder for the filter command specified in the configuration.
                ProcessBuilder filter = new ProcessBuilder(dConfig.filterCommand.split(" "));
                c.waitFor();
                Process f = filter.start();
                f.waitFor();
            }

            XmlMapper xmlMapper = new XmlMapper();
            OsmModel osmModel;
            try (InputStream fileStream = new FileInputStream("data/output.osm")) {
                osmModel = xmlMapper.readValue(fileStream, OsmModel.class);
            }

            List<Node> nodes = osmModel.getNodes();
            // Create a processing pipeline for filtering nodes.
            Pipe<List<Node>, List<CulturalHeritage>> pipe =
                    new Pipe<>(new TagRemovalFilter())
                            .chain(new NullNamesRemovalFilter())
                            .chain(new BoundaryStonesRemoval());

            List<CulturalHeritage> chList = pipe.process(nodes);

            // Obtain and set location information for each cultural heritage site.
            chList.forEach( culturalHeritage -> {
                HashMap<String, String> locationInfo = this.locationService.getLocationInfo(culturalHeritage.lat, culturalHeritage.lon);
                culturalHeritage.setAddress(locationInfo.get("address"));
                culturalHeritage.setCity(locationInfo.get("city"));
            });

            heritageRepository.saveAll(chList);
        }
    }

    // Method to retrieve all cultural heritage sites.
    @Override
    public List<CulturalHeritage> getAll() {
        return heritageRepository.findAll();
    }
}
