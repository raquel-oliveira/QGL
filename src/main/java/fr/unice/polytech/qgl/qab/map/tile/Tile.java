package fr.unice.polytech.qgl.qab.map.tile;


import fr.unice.polytech.qgl.qab.map.tile.square.Square;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 8/12/16
 *
 * Class that represent the Tile in the map
 */
public class Tile {
    private TileType type;
    private Creek creek;
    private boolean wasVisited;
    private List<Square> squares;
    private List<Biomes> biomesPredominant;

    /**
     * Tile's constructor
     */
    public Tile() {
        this.type = TileType.UNDEFINED;
        creek = new Creek();
        squares = new ArrayList<>();
        wasVisited = false;
        biomesPredominant = new ArrayList<>();
    }


    /**
     * Tile's contructor with parametre
     * @param type of the tile
     */
    public Tile(TileType type) {
        this.type = type;
    }

    /**
     * Set the type of the tile
     * @param type type of the tile
     */
    public void setType(TileType type) { this.type = type; }

    /**
     * Get the tile type
     * @return the tile type
     */
    public TileType getType() { return type; }

    /**
     * Set the creek in the tile
     * @param creek to add in the tile
     */
    public void setCreek(Creek creek) { this.creek = creek; }

    /**
     * Get the creeks in the tile
     * @return creek in the tile
     */
    public Creek getCreek() { return creek; }

    /**
     * Check if the tile was visited
     * @return if the tile was visited
     */
    public boolean wasVisited() {
        return wasVisited;
    }

    /**
     * Set the tile as visited
     * @param visit
     */
    public void setVisit(boolean visit) { wasVisited = visit; }

    /**
     * Get the list of square
     * @return list of square
     */
    public List<Square> getSquares() {
        return squares;
    }

    /**
     * Set a list of square
     * @param squares new list
     */
    public void setSquares(List<Square> squares){
        this.squares = squares;
    }

    public void setBiomesPredominant(List<Biomes> biomesPredominant) {
        this.biomesPredominant = biomesPredominant;
    }
}
