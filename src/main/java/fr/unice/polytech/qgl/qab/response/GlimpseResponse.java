package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 04/02/16.
 */
public class GlimpseResponse {
    private int asked_range;
    private List<HashMap<Biomes, Double>> initial_tiles;
    private List<Biomes> third_tile;
    private Biomes fourth_tile;

    public GlimpseResponse() {
        asked_range = 0;
        initial_tiles = new ArrayList<>();
        third_tile = new ArrayList<>();
        fourth_tile = null;
    }

    public void setAsked_range(int asked_range){
        this.asked_range = asked_range;
    }

    public void setFourth_tile(Biomes fourth_tile) {
        this.fourth_tile = fourth_tile;
    }

    public void setInitial_tiles(List<HashMap<Biomes, Double>> initial_tiles) {
        this.initial_tiles = initial_tiles;
    }

    public void setThird_tile(List<Biomes> third_tile) {
        this.third_tile = third_tile;
    }

    public int getAsked_range() {
        return asked_range;
    }

    public List<HashMap<Biomes, Double>> getInitial_tiles() {
        return initial_tiles;
    }

    public List<Biomes> getThird_tile() {
        return third_tile;
    }

    public Biomes getFourth_tile() {
        return fourth_tile;
    }
}
