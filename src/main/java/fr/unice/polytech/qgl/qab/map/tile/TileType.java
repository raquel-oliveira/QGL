package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @version 8/12/16
 *
 * Enum that represent the tile types
 */
public enum TileType {
    GROUND("ground"),
    OCEAN("ocean"),
    UNDEFINED("undefined");

    private String name = "";

    TileType(String name) { this.name = name; }

    @Override
    public String toString() {
        try { return name; }
        catch (NullPointerException e) { throw new NullPointerException("TileType is Null"); }
    }

    public boolean isEquals(Object type) {
        return type.toString().equalsIgnoreCase(name);
    }

    public static TileType fromString(String tileType){
        if (tileType != null){
            for (TileType type : TileType.values()){
                if (type.toString().equalsIgnoreCase(tileType)) return type;
            }
        }
        return null;
    }
}
