package fr.unice.polytech.qgl.qab.util.enums;

/**
 * @version 01/12/15.
 */
public enum Phase {
    GROUND("GROUND"),
    AERIAL("AERIAL");

    private String name = "";

    Phase(String name) {
            this.name = name;
    }

    public boolean isEquals(Phase phase) {
        return phase.toString().equalsIgnoreCase(name);
    }
}
