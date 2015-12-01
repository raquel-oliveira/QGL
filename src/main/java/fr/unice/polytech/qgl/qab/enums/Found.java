package fr.unice.polytech.qgl.qab.enums;

/**
 * Enum to represent the items founded.
 *
 * @version 4.9
 */
public enum Found {
    GROUND("GROUND"),
    OUT_OF_RANGE("OUT_OF_RANGE");

    private String name = "";

    Found(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Found found) {
        if (found.toString().equalsIgnoreCase(name)) return true;
        return false;
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
