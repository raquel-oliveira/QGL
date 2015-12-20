package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

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
            this.height += (height + 1);
            this.definedHeight = definedHeight;
        }
    }

    public void initializeWidthMap(int width, boolean definedWidth) {
        if (!this.definedWidth) {
            this.width += (width + 1);
            this.definedWidth = definedWidth;
        }
    }

    public void initializeTileUndefined(Position position) throws PositionOutOfMapaRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapaRange("Value out of map range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.UNDEFINED));
    }

    public void initializeTileGround(Position position) throws PositionOutOfMapaRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapaRange("Value out of map range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.GROUND));
    }

    public void initializeTileOcean(Position position) throws PositionOutOfMapaRange {
        //if (position.getX() >= width || position.getY() >= height)
        //    throw new PositionOutOfMapaRange("Value out of map range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.OCEAN));
    }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public TileType getTileType(Position position) { return tiles.get(position).getType(); }

    public Position getLastPosition() { return lastPosition; }

    public void setLastPosition(Position position) { lastPosition = position; }

    public boolean isDefinedHeight() { return definedHeight; }

    public boolean isDefinedWidth() { return definedWidth; }

    public boolean isEmpty() { return tiles.isEmpty(); }

    public boolean returnGround() {
        return returnToGround;
    }

    public void setReturnGround(boolean returnToGround) {
        this.returnToGround = returnToGround;
    }

    /**
     * Method to calculate the distance between the position gave as parameter.
     * @param position
     * @param direction
     * @return
     */
    public int distanceOutOfRange(Position position, Direction direction) {
        if (direction.isHorizontal()) {
            if (direction.isEquals(Direction.WEST)) {
                return width - position.getX();
            } else if (direction.isEquals(Direction.WEST)) {
                return position.getX() - width;
            }
        } else {
            if (direction.isEquals(Direction.NORTH)) {
                return width - position.getX();
            } else {
                return position.getX() - width;
            }
        }
        return -1;
    }

    public boolean hasSpace(Direction direction) {
        if (direction.isHorizontal()) {
            if (direction.isEquals(Direction.WEST)) {
                if (width - lastPosition.getX() == 1) return false;
            } else if (direction.isEquals(Direction.WEST)) {
                if (lastPosition.getX() - width == 1) return false;
            }
        } else {
            if (direction.isEquals(Direction.NORTH)) {
                if (width - lastPosition.getX() == 1) return false;
            } else {
                if (lastPosition.getX() - width == 1) return false;
            }
        }
        return true;
    }
}