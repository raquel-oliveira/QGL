package fr.unice.polytech.qgl.qab.enums;

/**
 * Enum to represents the directions available in the game.
 *
 * @version 4.8
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
}