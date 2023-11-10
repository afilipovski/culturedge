import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import models.CulturalHeritage;
import models.DConfig;
import models.OsmModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
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
        OsmModel osmModel = new OsmModel();
        try(InputStream fileStream = new FileInputStream("data/output.osm")) {
            osmModel = xmlMapper.readValue(fileStream, OsmModel.class);
        }
        List<CulturalHeritage> chList = new ArrayList<>();
        osmModel.nodes.forEach(n -> chList.add(new CulturalHeritage(
                n.getProperty("name"),
                n.lat,
                n.lon,
                n.getProperty("historic"),
                n.getProperty("tourism")
        )));
        chList.forEach(System.out::println);
        System.out.println(osmModel.nodes.size());
    }
}