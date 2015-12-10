package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 4.9
 */
public class Discovery {
    private Found found;
    private int range;
    private Direction direction;

    public Discovery() {
        this.found = Found.UNDEFINED;
        this.range = 0;
        this.direction = null;
    }

    public Discovery(Found found, int range) {
        this.found = found;
        this.range = range;
    }

    public Discovery(Found found, int range, Direction direction) {
        this.found = found;
        this.range = range;
        this.direction = direction;
    }

    public Found getFound() {
        return found;
    }

    public int getRange() {
        return range;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setFound(Found found) {
        this.found = found;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}