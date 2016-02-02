package fr.unice.polytech.qgl.qab.resources.Primary;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Raquel on 20/12/2015.
 * @version 01/02/2016
 */
public enum PrimaryType {
    FISH,
    FLOWER,
    FRUITS,
    FUR,
    ORE,
    QUARTZ,
    SUGAR_CANE,
    WOOD;

    private Set<Biomes> biomes = new HashSet<>(); //todo: change to static?

    /**
     * Coonstructor of Resource
     */
    PrimaryType() {
        switch (this) {
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
        }

    }

    public Set<Biomes> getBiome(){
        return biomes;
    }
}
