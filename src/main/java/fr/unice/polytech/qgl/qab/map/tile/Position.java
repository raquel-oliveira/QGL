package fr.unice.polytech.qgl.qab.map.tile;

/**
 * @version 8/12/16
 *
 * Class the represent the positon inf the map
 */
public class Position {
    private int x;
    private int y;

    /**
     * Position's constructor
     * @param x position x
     * @param y position y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the coordinate X
     * @return coordinate X
     */
    public int getX() {
        return x;
    }

    /**
     * Return the coordinate Y
     * @return coordinate Y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the coordinate X
     * @param x coordinate X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the coordinate Y
     * @param y coordinate Y
     */
    public void setY(int y) {
        this.y = y;
    }
}
