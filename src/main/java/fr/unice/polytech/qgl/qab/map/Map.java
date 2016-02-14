package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;

import java.util.HashMap;

/**
 * @version 8.12.2016
 *
 * Class that represent the map of the simulation
 */
public class Map {
    private HashMap<Position, Tile> tiles;
    private int height;
    private int width;

    // save the last plane position
    private Position lastPosition;
    // check if I have the final height and width
    private boolean definedHeight, definedWidth;
    // If the plane is in Ground, I don't need return to the Ground
    private boolean returnToGround;

    /**
     * Map's constructor
     */
    public Map() {
        tiles = new HashMap<>();
        height = -1;
        width = -1;
        returnToGround = false;
    }

    /**
     * Method that initialize the height of the map
     * @param height value height
     * @param definedHeight if is the final value, or just a part of the dimention
     */
    public void initializeHeightMap(int height, boolean definedHeight) {
        if (!this.definedHeight) {
            this.height += (this.height == - 1)?height + 1:height;
            this.definedHeight = definedHeight;
        }
    }

    /**
     * Method that initialize the width map
     * @param width value width
     * @param definedWidth if is the final value, or just a part of the dimention
     */
    public void initializeWidthMap(int width, boolean definedWidth) {
        if (!this.definedWidth) {
            this.width += (this.width == - 1)?width + 1:width;
            this.definedWidth = definedWidth;
        }
    }

    /**
     * If I need initialize a tile but i dont know if is ground or ocean
     * @param position
     * @throws PositionOutOfMapRange
     */
    public void initializeTileUndefined(Position position) throws PositionOutOfMapRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapRange("Value out of map range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.UNDEFINED));
    }

    /**
     * If I need initialize a tile as ground
     * @param position
     * @throws PositionOutOfMapRange
     */
    public void initializeTileGround(Position position) throws PositionOutOfMapRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapRange("Value out of map range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.GROUND));
    }

    /**
     * If I need initialize a tile as ocean
     * @param position
     * @throws PositionOutOfMapRange
     */
    public void initializeTileOcean(Position position) throws PositionOutOfMapRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapRange("Value out of map range!");
        if (position.getX() < 0 || position.getY() < 0)
            throw new PositionOutOfMapRange("It's not possible values negatives to the positions!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.OCEAN));
    }

    /**
     * Get the map height
     * @return map height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the map width
     * @return map width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the type of the tile
     * @param position of the tile
     * @return the tile type
     */
    public TileType getTileType(Position position) {
        return tiles.get(position).getType();
    }

    /**
     * Get the last position visited
     * @return last position visited
     */
    public Position getLastPosition() {
        return lastPosition;
    }

    /**
     * Set the last position visited
     * @param position mas position visited
     */
    public void setLastPosition(Position position) {
        lastPosition = position;
    }

    /**
     * Check if the set of tiles is empty
     * @return
     */
    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}