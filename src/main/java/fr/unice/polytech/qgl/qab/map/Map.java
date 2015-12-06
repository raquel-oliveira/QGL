package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.tile.Tile;
import fr.unice.polytech.qgl.qab.tile.TileType;

import java.util.ArrayList;

/**
 * @version 4.9
 */
public class Map {
    private Tile[][] tiles;

    public Map() {}

    public void initializeMap(int x, int y) {
        tiles = new Tile[x][y];
    }

    public void initializeTitleUndefined(int x, int y) {
        tiles[x][y] = new Tile();
    }

    public void initializeTitleGround(int x, int y) { tiles[x][y] = new Tile(TileType.GROUND); }

    public void initializeTitleOcean(int x, int y) { tiles[x][y] = new Tile(TileType.OCEAN); }

    public int getColumns() { return tiles[0].length; }

    public int getRows() { return tiles.length; }

    public TileType getTitleType(int x, int y) {
        return tiles[x][y].getType();
    }

    public boolean isEmpty() {
        if (tiles == null) {
            return true;
        }
        return false;
    }
}
