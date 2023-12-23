package com.example.culturedge.repository;

import com.example.culturedge.filters.BoundaryStonesRemoval;
import com.example.culturedge.filters.NullNamesRemovalFilter;
import com.example.culturedge.filters.TagRemovalFilter;
import com.example.culturedge.models.CulturalHeritage;
import com.example.culturedge.helpers.DConfig;
import com.example.culturedge.helpers.Node;
import com.example.culturedge.helpers.OsmModel;
import com.example.culturedge.pipe.Pipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HeritageRepository {
    List<CulturalHeritage> inMemoryData;

    public HeritageRepository() throws IOException, InterruptedException {
        inMemoryData = new ArrayList<>();

        File outputFile = new File("data/output.osm");
        if (!outputFile.exists()) {
            DConfig dConfig;
            ObjectMapper objectMapper = new ObjectMapper();
            try (InputStream fileStream = new FileInputStream("config.json")) {
                dConfig = objectMapper.readValue(fileStream, DConfig.class);
            }
            ProcessBuilder converter = new ProcessBuilder(dConfig.converterCommand.split(" "));
            Process c = converter.start();
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
        Pipe<List<Node>, List<CulturalHeritage>> pipe =
                new Pipe<>(new TagRemovalFilter())
                        .chain(new NullNamesRemovalFilter())
                        .chain(new BoundaryStonesRemoval());

        List<CulturalHeritage> chList = pipe.process(nodes);
        inMemoryData.addAll(chList);
    }

    public List<CulturalHeritage> getAll() {
        return inMemoryData;
    }
}
