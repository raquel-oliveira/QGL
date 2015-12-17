package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @version 12.12.2015
 */
public class Biome {
    private String name;

    public Biome() {
        name = "";
    }

    public Biome(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

