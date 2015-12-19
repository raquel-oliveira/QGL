package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;

import java.util.HashMap;

/**
 * @version 8.12.2016
 */
public class Map {
    private HashMap<Position, Tile> tiles;
    private static final String MESSAGE_ERROR = "Value out of map range!";
    private int height;
    private int width;

    // save the last plane position
    private Position lastPosition;
    // If the plane is in Ground, I don't need return to the Ground
    private boolean returnToGround;

    public Map() {
        tiles = new HashMap<>();
        height = 0;
        width = 0;
        returnToGround = false;
    }

    public void initializeMap(int height, int width) {
        this.tiles = new HashMap<>();
        this.height = height;
        this.width = width;
    }

    public void initializeTileUndefined(Position position) throws PositionOutOfMapaRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapaRange(MESSAGE_ERROR );
        lastPosition = position;
        tiles.put(position, new Tile(TileType.UNDEFINED));
    }

    public void initializeTileGround(Position position) throws PositionOutOfMapaRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapaRange(MESSAGE_ERROR);
        lastPosition = position;
        returnToGround = false;
        tiles.put(position, new Tile(TileType.GROUND));
    }

    public void initializeTileOcean(Position position) throws PositionOutOfMapaRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapaRange(MESSAGE_ERROR);
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

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public boolean returnGround() {
        return returnToGround;
    }
}