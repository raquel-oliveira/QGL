package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

/**
 * @version 06/03/16.
 */
public class MapHandler {
    private MapHandler() {
    }

    public static void updatePositionX(int x, Map map, Direction dir) {
        if (dir.equals(Direction.EAST))
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() + x);
        else
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() - x);
    }

    public static void updatePositionY(int y, Map map, Direction dir) {
        if (dir.equals(Direction.NORTH))
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() - y);
        else
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() + y);
    }

    public static int calcDistX(Position p, Position lastPosition) {
        return Math.abs(p.getX() - lastPosition.getX());
    }

    public static int calcDistY(Position p, Position lastPosition) {
        return Math.abs(p.getY() - lastPosition.getY());
    }

    public static double getDistance(Position current, Position p) {
        double distX = getDistX(current, p);
        double distY = getDistY(current, p);
        return distX + distY;
    }

    public static double getDistY(Position current, Position p) {
        return (double) Math.abs(p.getY() - current.getY());
    }

    public static double getDistX(Position current, Position p) {
        return (double) Math.abs(p.getX() - current.getX());
    }
}
