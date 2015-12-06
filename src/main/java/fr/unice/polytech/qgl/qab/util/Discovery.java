package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.enums.Found;

/**
 * @version 4.9
 */
public class Discovery {
    private Found found;
    private int range;

    public Discovery(Found found, int range) {
        this.found = found;
        this.range = range;
    }

    public Found getFound() {
        return found;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}