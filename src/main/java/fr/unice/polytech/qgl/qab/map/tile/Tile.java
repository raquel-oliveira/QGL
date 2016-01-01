package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @author Gabriela
 * @version 4.9
 */
public class Tile {
    private TileType type;
    private Creek creek;
    private boolean wasVisited;

    public Tile() {
        this.type = TileType.UNDEFINED;
        creek = new Creek();
        wasVisited = false;
    }

    public Tile(TileType type) {
        this.type = type;
    }

    public void setType(TileType type) { this.type = type; }

    public TileType getType() { return type; }

    public void setCreek(Creek creek) { this.creek = creek; }

    public Creek getCreek() { return creek; }

    public boolean wasVisited() {
        return wasVisited;
    }

    public void setVisit(boolean visit) { wasVisited = visit; }
}
