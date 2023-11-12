import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import filters.BoundaryStonesRemoval;
import filters.NullNamesRemovalFilter;
import filters.TagRemovalFilter;
import models.CulturalHeritage;
import models.DConfig;
import models.Node;
import models.OsmModel;
import pipe.Pipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("data/cultural_heritage.json"), chList);
        chList.forEach(System.out::println);
        System.out.println(chList.size());
    }
}