package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * Class that represents the Echo response.
 * @version 19/02/16.
 */
public class EchoResponse {
    private Found found;
    private Direction direction;
    private int range;

    /**
     * EchoResponse's constructor
     */
    public EchoResponse() {
        this.found = Found.UNDEFINED;
        this.direction = null;
        this.range = 0;
    }

    /**
     * Add data in the instance of the response.
     * @param found what was found
     * @param direction the direction
     * @param range the range of the echo
     */
    public void addData(Found found, Direction direction, int range) {
        this.found = found;
        this.direction = direction;
        this.range = range;
    }

    /**
     * Return what was found
     * @return what was found
     */
    public Found getFound() {
        return found;
    }

    /**
     * Return the range of the echo
     * @return the range of the echo
     */
    public int getRange() {
        return range;
    }

    /**
     * Return the direction of the echo
     * @return the direction of the echo
     */
    public Direction getDirection() {
        return direction;
    }
}
