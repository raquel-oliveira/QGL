package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @version 8.12.2016
 * TODO: I can inicializa the main square
 * TODO: make a flag to be sure that i defined the dimension
 */
public class Map {
    private HashMap<Position, Tile> tiles;
    private int height;
    private int width;
    // save the last position saved
    private Position lastPosition;
    // check if I have the final height and witdh
    private boolean definedHeight, definedWitdh;
    // I need know that the plane is in Ground, when it get out, I will know
    private boolean isInGround;
    // If the plane is in Ground, I dont need return to the Ground
    private boolean returnGround;

    public Map() {
        tiles = new HashMap<>();
        height = 0;
        width = 0;
        isInGround = false;
        returnGround = false;
    }

    public void initializeMap(int height, int witdh, boolean definedHeigth, boolean definedWitdh) {
        tiles = new HashMap<>();
        this.height = height;
        this.width = witdh;
        this.definedHeight = definedHeigth;
        this.definedWitdh = definedWitdh;
    }

    //TODO: this is realy necessary?
    public void initializeTiteUndefined(Position position) {
        if (position.getX() >= width || position.getY() >= height)
            throw new ArrayIndexOutOfBoundsException("Value out of range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.UNDEFINED));
    }

    public void initializeTiteGround(Position position) {
        if (position.getX() >= width || position.getY() >= height)
            throw new ArrayIndexOutOfBoundsException("Value out of range!");
        lastPosition = position;
        isInGround = true;
        returnGround = false;
        // add this position in the map
        tiles.put(position, new Tile(TileType.GROUND));
    }

    public void initializeTiteOcean(Position position) {
        if (position.getX() >= width || position.getY() >= height)
            throw new ArrayIndexOutOfBoundsException("Value out of range!");
        lastPosition = position;
        if (isInGround) returnGround = true;
        isInGround = false;
        tiles.put(position, new Tile(TileType.OCEAN));
    }

    public Discovery existGround(Direction head) { //TODO:direcao
        Discovery discovery = new Discovery();
        discovery.setFound(Found.GROUND);
        for(int y = lastPosition.getY(); tiles.containsKey(new Position(lastPosition.getX(), y)); y++) {
            if (tiles.get(new Position(lastPosition.getX(), y)).getType().equals(TileType.GROUND)) {
                discovery.setRange(y - lastPosition.getX());
                discovery.setDirection(head);

            }
        }
        return null;
    }

    public int getHeigth() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public TileType getTiteType(Position position) {
        return tiles.get(position).getType();
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public boolean isDefinedHeigth() {
        return definedHeight;
    }

    public boolean isDefinedWitdh() {
        return definedWitdh;
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    /**
     * Method to check if the plane visited tile in the map of Noth or South.
     * @param
     * @return
     */
    public int hasTileVisitedNothSouth() {
        int response = 0;
        Iterator it = tiles.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry)it.next();
            Position pos = (Position)pair.getKey();
            if(pos.getY() > lastPosition.getY()) {
                if (response == 0) response = 1;
                else response = 3;
            }
            if(pos.getY() < lastPosition.getY()) {
                if (response == 0) response = 2;
                else response = 3;
            }
        }
        return response;
    }

    public int hasTileVisitedWestEast() {
        int response = 0;
        Iterator it = tiles.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry)it.next();
            Position pos = (Position)pair.getKey();
            if(pos.getX() > lastPosition.getX()) {
                if (response == 0) response = 1;
                else response = 3;
            }
            if(pos.getX() < lastPosition.getX()) {
                if (response == 0) response = 2;
                else response = 3;
            }
        }
        return response;
    }

    /**
     * Method to calculate the distance between the position gave as parameter.
     * @param position
     * @param direction
     * @return
     */
    public int distanceOutOfRange(Position position, Direction direction) {
        if (direction.isHorizontal()) {
            if (direction.equals(Direction.WEST)) {
                return width - position.getX();
            } else if (direction.equals(Direction.WEST)) {
                return position.getX() - width;
            }
        } else {
            if (direction.equals(Direction.NORTH)) {
                return width - position.getX();
            } else {
                return position.getX() - width;
            }
        }
        return -1;
    }

    public boolean returnGround() {
        return returnGround;
    }

    public boolean hasSpace(Direction direction) {
        if (direction.isHorizontal()) {
            if (direction.equals(Direction.WEST)) {
                if (width - lastPosition.getX() == 1) return false;
            } else if (direction.equals(Direction.WEST)) {
                if (lastPosition.getX() - width == 1) return false;
            }
        } else {
            if (direction.equals(Direction.NORTH)) {
                if (width - lastPosition.getX() == 1) return false;
            } else {
                if (lastPosition.getX() - width == 1) return false;
            }
        }
        return true;
    }
}
