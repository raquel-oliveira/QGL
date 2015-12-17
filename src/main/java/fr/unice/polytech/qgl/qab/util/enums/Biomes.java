package fr.unice.polytech.qgl.qab.util.enums;

/**
 * @version 12/12/2015.
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

    public boolean isCommon(){
        return this == OCEAN || this == LAKE || this == BEACH || this == GRASSLAND;
    }

    public boolean isTropical(){
        return this == MANGROVE || this == TROPICAL_RAIN_FOREST || this == TROPICAL_SEASONAL_FOREST;
    }

    public boolean isTemperate(){
        return this == TEMPERATE_DECIDUOUS_FOREST || this == TEMPERATE_DESERT || this == TEMPERATE_RAIN_FOREST;
    }

    public boolean isNordic(){
        if (this == TAIGA || this == SNOW || this == TUNDRA)
            return true;
        if (this == ALPINE || this == GLACIER)
            return true;
        return false;
    }

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

    public TypeBiome typeBiome(Biomes biome){
        if (biome != null){
            if (isCommon()) return TypeBiome.COMMON;
            else if (isNordic()) return TypeBiome.NORDIC;
            else if (isTropical()) return  TypeBiome.TROPICAL;
            else if (isSubTropical()) return TypeBiome.SUBTROPICAL;
            else if (isTemperate()) return TypeBiome.TEMPERATE;
            else return null;
        }
        return null;
    }
}
