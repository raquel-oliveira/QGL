package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

/**
 * Class to handle with the map information
 * @version 06/03/16.
 */
public class MapHandler {
    private MapHandler() {
    }

    /**
     * Update the last position when the explorers move
     * @param x position x
     * @param map current map data
     * @param dir move direction
     */
    public static void updatePositionX(int x, Map map, Direction dir) {
        if (dir.equals(Direction.EAST))
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() + x);
        else
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() - x);
    }

    /**
     * Update the last position when the explorers move
     * @param y position y
     * @param map current map data
     * @param dir move direction
     */
    public static void updatePositionY(int y, Map map, Direction dir) {
        if (dir.equals(Direction.NORTH))
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() - y);
        else
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() + y);
    }

    /**
     * Calcule the distance between two coordenates x
     * @param p postion p
     * @param lastPosition last postion of the explorers
     * @return return the distance
     */
    public static int calcDistX(Position p, Position lastPosition) {
        return Math.abs(p.getX() - lastPosition.getX());
    }

    /**
     * Calcule the distance between two coordenates y
     * @param p postion p
     * @param lastPosition last postion of the explorers
     * @return return the distance
     */
    public static int calcDistY(Position p, Position lastPosition) {
        return Math.abs(p.getY() - lastPosition.getY());
    }

    /**
     * Get the distance between two positions
     * @param current current position
     * @param p position to go
     * @return the distance
     */
    public static int getDistance(Position current, Position p) {
        int distX = getDistX(current, p);
        int distY = getDistY(current, p);
        return distX + distY;
    }

    /**
     * Get the Distance of the axis Y between two positions
     * @param current current position
     * @param p second position
     * @return the distance of the axis Y
     */
    public static int getDistY(Position current, Position p) {
        return Math.abs(p.getY() - current.getY());
    }

    /**
     * Get the Distance of the axis X between two positions
     * @param current current position
     * @param p second position
     * @return the distance of the axis X
     */
    public static int getDistX(Position current, Position p) {
        return Math.abs(p.getX() - current.getX());
    }
}
