package filters;

import models.CulturalHeritage;

import java.util.List;
import java.util.stream.Collectors;

public class BoundaryStonesRemoval implements Filter<List<CulturalHeritage>, List<CulturalHeritage>> {
    @Override
    public List<CulturalHeritage> execute(List<CulturalHeritage> input) {
        return input.stream().filter(culturalHeritage -> culturalHeritage.historic != null &&
                        !culturalHeritage.historic.equals("boundary_stone"))
                .collect(Collectors.toList());
    }
}
