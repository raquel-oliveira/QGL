package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @author Gabriela
 * @version 4.9
 */
public enum TileType {
    GROUND("ground"),
    OCEAN("ocean"),
    UNDEFINED("undefined");

    private String name = "";

    TileType(String name) { this.name = name; }

    public String toString() {
        try { return name; }
        catch (NullPointerException e) { throw new NullPointerException("TileType is Null"); }
    }

    public boolean equals(TileType type) {
        return (type.toString().equalsIgnoreCase(name));
    }

    public static TileType fromString(String tiletype){
        if (tiletype != null){
            for (TileType type : TileType.values()){
                if (type.toString().equalsIgnoreCase(tiletype)) return type;
            }
        }
        return null;
    }
}
