package filters;

import models.CulturalHeritage;
import models.Node;

public class TagRemovalFilter implements Filter<Node, CulturalHeritage> {

    @Override
    public CulturalHeritage execute(Node input) {
        return new CulturalHeritage(
                input.getProperty("name"),
                input.lat,
                input.lon,
                input.getProperty("historic"),
                input.getProperty("tourism")
        );
    }
}
