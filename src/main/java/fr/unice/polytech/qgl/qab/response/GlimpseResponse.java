package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 04/02/16.
 *
 * Class that represent the glimpse response structure
 */
public class GlimpseResponse {
    private int asked_range;
    private List<HashMap<Biomes, Double>> initialTiles;
    private List<Biomes> thirdTile;
    private Biomes fourthTile;

    /**
     * GlimpseResponse's constructor
     */
    public GlimpseResponse() {
        asked_range = 0;
        initialTiles = new ArrayList<>();
        thirdTile = new ArrayList<>();
        fourthTile = null;
    }

    /**
     * Set the value of the asked range
     * @param asked_range
     */
    public void setAskedRange(int asked_range){
        this.asked_range = asked_range;
    }

    /**
     * Set the list of biomes for the fourth tile
     * @param fourthTile the value of the fourth tile
     */
    public void setFourthTile(Biomes fourthTile) {
        this.fourthTile = fourthTile;
    }

    /**
     * Set the list of biomes for the initial tiles
     * @param initialTiles the value of the initial tiles
     */
    public void setInitialTiles(List<HashMap<Biomes, Double>> initialTiles) {
        this.initialTiles = initialTiles;
    }

    /**
     * Set the list of biomes for the third tile
     * @param thirdTile the value of the third tile
     */
    public void setThirdTile(List<Biomes> thirdTile) {
        this.thirdTile = thirdTile;
    }

    /**
     * Get the list of biomes of the inital tiles
     * @return the value of the inital tiles
     */
    public List<HashMap<Biomes, Double>> getInitialTiles() {
        return initialTiles;
    }

    /**
     * Get the list of biomes of the third tile
     * @return the value of the third tile
     */
    public List<Biomes> getThirdTile() {
        return thirdTile;
    }

    /**
     * Get the list of biomes of the fourth tile
     * @return the value of the fourth tile
     */
    public Biomes getFourthTile() {
        return fourthTile;
    }

    /**
     * check if there was response
     * @return
     */
    public boolean hasResponse() {
        return asked_range > 0;
    }
}
