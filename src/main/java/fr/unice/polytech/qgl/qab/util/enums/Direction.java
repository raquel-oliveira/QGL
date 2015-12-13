package fr.unice.polytech.qgl.qab.util.enums;

import java.util.Random;

/**
 * Enum to represents the directions available in the game.
 *
 * @version 4.9
 */
public enum Direction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private String name = "";

    /**
     * Constructor enum
     * @param name
     */
    Direction(String name) {
        this.name = name;
    }

    /**
     * Method that return if the direction is horizontal
     * @return true if the direction if horizontal, false if not
     */
    public Boolean isHorizontal() {
        return this == EAST || this == WEST;
    }

    /**
     * Method that return if the direction if vertical
     * @return true if the direction if vertical, false if not
     */
    public Boolean isVertical() {
        return this == NORTH || this == SOUTH;
    }

    /**
     * Method that change the direction to String
     * @return name
     */
    public String toString() {
        return name;
    }

    public boolean equals(Direction direction) {
        return (direction.toString().equalsIgnoreCase(name));
    }

    /**
     * Method that receive a direction like string and return a object Direction
     * @param direction string with the direction
     * @return object Direction
     */
    public static Direction fromString(String direction){
        if (direction != null){
            for (Direction d : Direction.values()){
                if (d.toString().equalsIgnoreCase(direction)) return d;
            }
        }
        return null;
    }

    public static Direction randomSideDirection(Direction direction) {
        Random rand = new Random();
        int side = rand.nextInt(2);
        if (side > 1)
            throw new RuntimeException("Value out of range");
        if (direction.isHorizontal())
            return (side == 0)? NORTH:SOUTH;
        if (direction.isVertical())
            return (side == 0)? WEST:EAST;
        return null;
    }
}