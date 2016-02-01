package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 4.9
 */
public class Discovery {
    private Found found;
    private int range;
    private Direction direction;
    private List<Biomes> biomes;
    private List<Creek> creeks;

    public Discovery() {
        this.found = Found.UNDEFINED;
        this.range = 0;
        this.direction = null;
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
    }

    public Discovery(Found found, int range, Direction direction) {
        this.found = found;
        this.range = range;
        this.direction = direction;
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
    }

    public Discovery(List<Biomes> biomes, List<Creek> creeks) {
        this.biomes = biomes;
        this.creeks = creeks;
    }

    public void setUp() {
        this.found = Found.UNDEFINED;
        this.range = 0;
        this.direction = null;
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
    }

    public Found getFound() { return found; }

    public int getRange() { return range; }

    public Direction getDirection() { return direction; }

    public List<Biomes> getBiomes() { return biomes; }

    public List<Creek> getCreeks() { return creeks; }

    public void setRange(int range) { this.range = range; }

    public void setFound(Found found) { this.found = found; }

    public void setDirection(Direction direction) { this.direction = direction; }

    public void setBiomes(List<Biomes> biomes) { this.biomes = biomes; }

    public void setCreeks(List<Creek> creeks) { this.creeks = creeks; }

    public boolean containsBiome(Biomes bio) {
        for (Biomes b: biomes) {
            if (b.equals(bio))
                return true;
        }
        return false;
    }

}