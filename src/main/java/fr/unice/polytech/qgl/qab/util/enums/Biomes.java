package fr.unice.polytech.qgl.qab.util.enums;

/**
 * Created by Raquel on 12/12/2015.
 */
public enum Biomes {
    OCEAN("OCEAN"), //: plain ocean, wide open area full of unknown;
    LAKE("LAKE"), //: internal lake, potentially big, with freshwater;
    BEACH(""), //: beach (not always sandy);
    GRASSLAND(""),//: area of prairie;

    MANGROVE(""),//: super wet area, home of the mangle tree;
    TROPICAL_RAIN_FOREST(""), //: hot and wet;
    TROPICAL_SEASONAL_FOREST(""),//: less wet, but not less hot;

    TEMPERATE_DECIDUOUS_FOREST(""), //: classical forests with trees that lose their leaves each year;
    TEMPERATE_RAIN_FOREST(""),//: very rare biome, very wet area coupled to low temperatures;
    TEMPERATE_DESERT(""),//: aride area with sparse vegetation and very few humidity;

    TAIGA(""),//: boreal forest, cold and majestuous;
    SNOW(""),//: area covered with snow, wet and cold;
    TUNDRA(""),//: arctic prairie, surviving on permanently frozen soil;
    ALPINE(""),//: rocky mountains, not always covered by snow;
    GLACIER(""),//: inhospitable area, full of ice;

    SHRUBLAND(""),//: prairie dominated by shrubs, such as maquis in Corsica or garrigue in Provence;
    SUB_TROPICAL_DESERT("");// very dry and inhospitable area

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

    public boolean equals(Biomes biomes) {
        if (biomes.toString().equalsIgnoreCase(name)) return true;
        return false;
    }

    public static Biomes fromString(Biomes biomes){
        if (biomes != null){
            for (Biomes s : Biomes.values()){
                if (s.toString().equalsIgnoreCase(biomes)) return s;
            }
        }
        return null;
    }

    public TypeBiome typeBiome(Biomes biome){
        if (biome != null){
            if (isNordic()) return TypeBiome.NORDIC;
            if (isTropical()) return  TypeBiome.TROPICAL;
            if (isSubTropical()) return TypeBiome.SUBTROPICAL;
            if (isTemperate()) return TypeBiome.TEMPERATE;
            return TypeBiome.COMMON;
        }
        return null;
    }
}
