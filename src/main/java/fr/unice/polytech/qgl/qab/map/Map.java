package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;

import java.util.HashMap;

/**
 * @version 8.12.2016
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

    public Map() {
        tiles = new HashMap<>();
        height = -1;
        width = -1;
        returnToGround = false;
    }

    public void initializeHeightMap(int height, boolean definedHeight) {
        if (!this.definedHeight) {
            this.height += (this.height == - 1)?height + 1:height;
            this.definedHeight = definedHeight;
        }
    }

    public void initializeWidthMap(int width, boolean definedWidth) {
        if (!this.definedWidth) {
            this.width += (this.width == - 1)?width + 1:width;
            this.definedWidth = definedWidth;
        }
    }

    public void initializeTileUndefined(Position position) throws PositionOutOfMapRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapRange("Value out of map range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.UNDEFINED));
    }

    public void initializeTileGround(Position position) throws PositionOutOfMapRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapRange("Value out of map range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.GROUND));
    }

    public void initializeTileOcean(Position position) throws PositionOutOfMapRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapRange("Value out of map range!");
        if (position.getX() < 0 || position.getY() < 0)
            throw new PositionOutOfMapRange("It's not possible values negatives to the positions!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.OCEAN));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public TileType getTileType(Position position) {
        return tiles.get(position).getType();
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position position) {
        lastPosition = position;
    }

    public boolean isDefinedHeight() {
        return definedHeight;
    }

    public boolean isDefinedWidth() {
        return definedWidth;
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public boolean returnGround() {
        return returnToGround;
    }

    public void setReturnGround(boolean returnToGround) {
        this.returnToGround = returnToGround;
    }
}