package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @author Gabriela
 * @version 4.9
 */
public class Tile {
    private TileType type;

    public Tile() {
        this.type = TileType.UNDEFINED;
    }

    public Tile(TileType type) {
        this.type = type;
    }

    public void setType(TileType type) { this.type = type; }

    public TileType getType() { return type; }
}
