package fr.unice.polytech.qgl.qab.util.enums;

/**
 * @version 12/12/2015.
 */
public enum Biomes {
    OCEAN("OCEAN"), //: plain ocean, wide open area full of unknown;
    LAKE("LAKE"), //: internal lake, potentially big, with freshwater;
    BEACH("BEACH"), //: beach (not always sandy);
    GRASSLAND("GRASSLAND"),//: area of prairie;

    MANGROVE("MANGROVE"),//: super wet area, home of the mangle tree;
    TROPICAL_RAIN_FOREST("TROPICAL_RAIN_FOREST"), //: hot and wet;
    TROPICAL_SEASONAL_FOREST("TROPICAL_SEASONAL_FOREST"),//: less wet, but not less hot;

    TEMPERATE_DECIDUOUS_FOREST("TEMPERATE_DECIDUOUS_FOREST"), //: classical forests with trees that lose their leaves each year;
    TEMPERATE_RAIN_FOREST("TEMPERATE_RAIN_FOREST"),//: very rare biome, very wet area coupled to low temperatures;
    TEMPERATE_DESERT("TEMPERATE_DESERT"),//: aride area with sparse vegetation and very few humidity;

    TAIGA("TAIGA"),//: boreal forest, cold and majestuous;
    SNOW("SNOW"),//: area covered with snow, wet and cold;
    TUNDRA("TUNDRA"),//: arctic prairie, surviving on permanently frozen soil;
    ALPINE("ALPINE"),//: rocky mountains, not always covered by snow;
    GLACIER("GLACIER"),//: inhospitable area, full of ice;

    SHRUBLAND("SHRUBLAND"),//: prairie dominated by shrubs, such as maquis in Corsica or garrigue in Provence;
    SUB_TROPICAL_DESERT("SUB_TROPICAL_DESERT");// very dry and inhospitable area

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
        return this == TAIGA || this == SNOW || this == TUNDRA || this == ALPINE || this == GLACIER;
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
