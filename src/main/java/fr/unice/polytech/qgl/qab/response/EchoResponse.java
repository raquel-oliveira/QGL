package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 19/02/16.
 */
public class EchoResponse {
    private Found found;
    private Direction direction;
    private int range;
    
    public EchoResponse() {
        this.found = Found.UNDEFINED;
        this.direction = null;
        this.range = 0;
    }
    
    public void addData(Found found, Direction direction, int range) {
        this.found = found;
        this.direction = direction;
        this.range = range;
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
}
