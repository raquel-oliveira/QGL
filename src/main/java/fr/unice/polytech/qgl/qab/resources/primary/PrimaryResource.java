package fr.unice.polytech.qgl.qab.resources.primary;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.Resource;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 01/02/16.
 *
 * Class that represent the primary resources
 */
public class PrimaryResource implements Resource {
    private PrimaryType resource;
    private Set<Biomes> biomes = new HashSet<>();

    /**
     * PrimaryResource's constructor
     * @param resource
     */
    public PrimaryResource(PrimaryType resource) {
        this.resource = resource;
        setBiomes();
    }

    @Override
    public String getName() {
        return resource.toString();
    }

    @Override
    public void setBiomes() {
        switch (resource) {
            case FISH:
                biomes.add(Biomes.OCEAN);
                biomes.add(Biomes.LAKE);
                break;
            case FLOWER:
                biomes.add(Biomes.ALPINE);
                biomes.add(Biomes.GLACIER);
                biomes.add(Biomes.MANGROVE);
                break;
            case FRUITS:
                biomes.add(Biomes.MANGROVE); //tropical biomes != tropical forest?
                biomes.add(Biomes.TROPICAL_RAIN_FOREST);
                biomes.add(Biomes.TROPICAL_SEASONAL_FOREST);
                break;
            case FUR:
                biomes.add(Biomes.GRASSLAND);
                biomes.add(Biomes.TEMPERATE_RAIN_FOREST);
                break;
            case ORE:
                biomes.add(Biomes.ALPINE);
                biomes.add(Biomes.SUB_TROPICAL_DESERT);
                biomes.add(Biomes.TEMPERATE_DESERT);
                break;
            case QUARTZ:
                biomes.add(Biomes.BEACH);
                biomes.add(Biomes.SUB_TROPICAL_DESERT);
                biomes.add(Biomes.TEMPERATE_DESERT);
                break;
            case SUGAR_CANE:
                biomes.add(Biomes.MANGROVE); //tropical biomes != tropical forest?
                biomes.add(Biomes.TROPICAL_RAIN_FOREST);
                biomes.add(Biomes.TROPICAL_SEASONAL_FOREST);
                break;
            case WOOD:
                biomes.add(Biomes.MANGROVE);
                biomes.add(Biomes.TEMPERATE_DECIDUOUS_FOREST);
                biomes.add(Biomes.TEMPERATE_RAIN_FOREST);
                biomes.add(Biomes.TROPICAL_RAIN_FOREST);
                biomes.add(Biomes.TROPICAL_SEASONAL_FOREST);
                break;
            default:
                break;
        }
    }

    @Override
    public Set<Biomes> getBiome(){
        return biomes;
    }

    public PrimaryType getResource(){return resource;}
}
