package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @author Gabriela
 * @version 4.9
 */
public class Tile {
    private TileType type;
    private boolean creek;
    private boolean wasVisited;

    public Tile() {
        this.type = TileType.UNDEFINED;
        creek = false;
        wasVisited = false;
    }

    public Tile(TileType type) {
        this.type = type;
    }

    public void setType(TileType type) { this.type = type; }

    public TileType getType() { return type; }

    public void setCreek(boolean creek) { this.creek = creek; }

    public boolean hasCreek() { return creek; }

    public boolean getCreek() { return creek; }

    public boolean isWasVisited() {
        return wasVisited;
    }
}
