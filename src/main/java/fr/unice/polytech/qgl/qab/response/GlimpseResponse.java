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
    private List<HashMap<Biomes, Double>> initialTiles;
    private List<Biomes> thirdTile;
    private Biomes fourthTile;

    public GlimpseResponse() {
        asked_range = 0;
        initialTiles = new ArrayList<>();
        thirdTile = new ArrayList<>();
        fourthTile = null;
    }

    public void setAskedRange(int asked_range){
        this.asked_range = asked_range;
    }

    public void setFourthTile(Biomes fourthTile) {
        this.fourthTile = fourthTile;
    }

    public void setInitialTiles(List<HashMap<Biomes, Double>> initialTiles) {
        this.initialTiles = initialTiles;
    }

    public void setThirdTile(List<Biomes> thirdTile) {
        this.thirdTile = thirdTile;
    }

    public List<HashMap<Biomes, Double>> getInitialTiles() {
        return initialTiles;
    }

    public List<Biomes> getThirdTile() {
        return thirdTile;
    }

    public Biomes getFourthTile() {
        return fourthTile;
    }
}
