package fr.unice.polytech.qgl.qab.map.tile;

import fr.unice.polytech.qgl.qab.util.enums.TypeBiome;

/**
 * @version 12/12/2015.
 *
 * Enum that represent the bimes' types
 */
public enum Biomes {
    OCEAN("OCEAN"),
    LAKE("LAKE"),
    BEACH("BEACH"),
    GRASSLAND("GRASSLAND"),

    MANGROVE("MANGROVE"),
    TROPICAL_RAIN_FOREST("TROPICAL_RAIN_FOREST"),
    TROPICAL_SEASONAL_FOREST("TROPICAL_SEASONAL_FOREST"),

    TEMPERATE_DECIDUOUS_FOREST("TEMPERATE_DECIDUOUS_FOREST"),
    TEMPERATE_RAIN_FOREST("TEMPERATE_RAIN_FOREST"),
    TEMPERATE_DESERT("TEMPERATE_DESERT"),

    TAIGA("TAIGA"),
    SNOW("SNOW"),
    TUNDRA("TUNDRA"),
    ALPINE("ALPINE"),
    GLACIER("GLACIER"),

    SHRUBLAND("SHRUBLAND"),
    SUB_TROPICAL_DESERT("SUB_TROPICAL_DESERT");

    private String name = "";

    Biomes(String name) {
        this.name = name;
    }

    /**
     * true if this biome is common
     * @return
     */
    public boolean isCommon(){
        return this == OCEAN || this == LAKE || this == BEACH || this == GRASSLAND;
    }

    /**
     * true if this biome is tropical
     * @return
     */
    public boolean isTropical(){
        return this == MANGROVE || this == TROPICAL_RAIN_FOREST || this == TROPICAL_SEASONAL_FOREST;
    }

    /**
     * true if this biome is temperate
     * @return
     */
    public boolean isTemperate(){
        return this == TEMPERATE_DECIDUOUS_FOREST || this == TEMPERATE_DESERT || this == TEMPERATE_RAIN_FOREST;
    }

    /**
     * true if this biome is nordic
     * @return
     */
    public boolean isNordic(){
        if (this == TAIGA || this == SNOW || this == TUNDRA)
            return true;
        if (this == ALPINE || this == GLACIER)
            return true;
        return false;
    }

    /**
     * return true if is subtropical
     * @return
     */
    public boolean isSubTropical(){
        return this == SHRUBLAND || this == SUB_TROPICAL_DESERT;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isEquals(Biomes biomes) {
        return biomes.toString().equalsIgnoreCase(name);
    }

    /**
     * return the biome's group
     * @return
     */
    public TypeBiome typeBiome(){
        if (this.isCommon())
            return TypeBiome.COMMON;
        else if (this.isNordic())
            return TypeBiome.NORDIC;
        else if (this.isTropical())
            return  TypeBiome.TROPICAL;
        else if (this.isSubTropical())
            return TypeBiome.SUBTROPICAL;
        else
            return TypeBiome.TEMPERATE;
    }
}
