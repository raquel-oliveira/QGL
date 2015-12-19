package fr.unice.polytech.qgl.qab.util.enums;

/**
 * Enum to represent the items founded.
 *
 * @version 4.9
 */
public enum Found {
    GROUND("GROUND"),
    OUT_OF_RANGE("OUT_OF_RANGE"),
    UNDEFINED("UNDEFINED");

    private String name = "";

    Found(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isEquals(Found found) {
        return found.toString().equalsIgnoreCase(name);
    }

    public static Found fromString(String found){
        if (found != null){
            for (Found f : Found.values()){
                if (f.toString().equalsIgnoreCase(found)) return f;
            }
        }
        return null;
    }
}
