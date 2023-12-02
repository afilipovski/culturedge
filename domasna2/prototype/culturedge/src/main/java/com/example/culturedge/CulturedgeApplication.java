package com.example.culturedge;

import com.example.culturedge.filters.BoundaryStonesRemoval;
import com.example.culturedge.filters.NullNamesRemovalFilter;
import com.example.culturedge.filters.TagRemovalFilter;
import com.example.culturedge.models.CulturalHeritage;
import com.example.culturedge.models.DConfig;
import com.example.culturedge.models.Node;
import com.example.culturedge.models.OsmModel;
import com.example.culturedge.pipe.Pipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.List;

@SpringBootApplication
public class CulturedgeApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(CulturedgeApplication.class, args);
    }

}
