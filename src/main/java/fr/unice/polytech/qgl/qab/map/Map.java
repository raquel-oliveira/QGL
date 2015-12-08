package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.Set;

/**
 * @version 8.12.2016
 */
public class Map {
    private HashMap<Position, Tile> tiles;
    private int heigth;
    private int witdh;
    private Position lastPosition;

    public Map() {
        tiles = new HashMap<>();
        heigth = 0;
        witdh = 0;
    }

    public void initializeMap(int h, int w) {
        heigth = h;
        witdh = w;
    }

    public void initializeTiteUndefined(Position position) {
        if (position.getX() >= witdh || position.getY() >= heigth)
            throw new ArrayIndexOutOfBoundsException("Value out of range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.UNDEFINED));
    }

    public void initializeTiteGround(Position position) {
        if (position.getX() >= witdh || position.getY() >= heigth)
            throw new ArrayIndexOutOfBoundsException("Value out of range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.GROUND));
    }

    public void initializeTiteOcean(Position position) {
        if (position.getX() >= witdh || position.getY() >= heigth)
            throw new ArrayIndexOutOfBoundsException("Value out of range!");
        lastPosition = position;
        tiles.put(position, new Tile(TileType.OCEAN));
    }

    public int getHeigth() {
        return heigth;
    }

    public int getWitdh() {
        return witdh;
    }

    public TileType getTiteType(Position position) {
        return tiles.get(position).getType();
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}
