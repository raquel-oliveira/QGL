package fr.unice.polytech.qgl.qab;

public enum Direction {
    NORTH("n"),
    EAST("e"),
    SOUTH("s"),
    WEST("w");

    public Boolean isHorizontal() {
        return this == EAST || this == WEST;
    }

    public Boolean isVertical() {
        return this == NORTH || this == SOUTH;
    }

    private String name = "";

    //Constructor
    Direction(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static Direction fromString(String direction){
        if (direction != null){
            for (Direction d : Direction.values()){
                if (d.toString().equalsIgnoreCase(direction)) return d;
            }
        }
        return null;
    }

}