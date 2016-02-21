package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

/**
 * @version 11/12/15
 *
 * Class responsible for update the data of the map
 */
public class UpdaterMap {

    /**
     * Method tha receive the context and the map, and update the dimension of the map.
     * @param context context data of the current simulation
     * @param map from the simulation
     */
    public void initializeDimensions(Context context, Map map) {
        if (context.getLastDiscovery().getEchoResponse().getDirection().equals(context.getFirstHead())) {
            if (context.getFirstHead().isHorizontal()) {
                setWidth(map, context.getLastDiscovery().getEchoResponse().getRange() + 1, true);
            } else {
                setHeight(map, context.getLastDiscovery().getEchoResponse().getRange() + 1, true);
            }
        } else {
            if (context.getFirstHead().isVertical()) {
                if (map.getWidth() >= 0) {
                    setWidth(map, context.getLastDiscovery().getEchoResponse().getRange() + 1, true);
                } else {
                    setWidth(map, context.getLastDiscovery().getEchoResponse().getRange(), false);
                }
            } else {
                if (map.getHeight() >= 0) {
                    setHeight(map, context.getLastDiscovery().getEchoResponse().getRange() + 1, true);
                } else {
                    setHeight(map, context.getLastDiscovery().getEchoResponse().getRange(), false);
                }
            }
        }
    }

    /**
     * Set the map height
     * @param map object map
     * @param value height
     * @param defined if is the value defined, or if is just a part of the dimention
     */
    public void setHeight(Map map, int value, boolean defined) {
        map.initializeHeightMap(value, defined);
    }

    /**
     * Set the map width
     * @param map object map
     * @param value widthe
     * @param defined if is the value defined, or if is just a part of the dimention
     */
    public void setWidth(Map map, int value, boolean defined) {
        map.initializeWidthMap(value, defined);
    }

    /**
     * after each fly, it's necessary update the position of the plane
     * @param context data context of simulation
     * @param map object map
     */
    public void updateLastPositionFly(Context context, Map map) {
        Position position = map.getLastPosition();

        if (context.getHeading().isHorizontal()) {
            if (context.getHeading().isEquals(Direction.EAST)) {
                map.setLastPosition(new Position(position.getX() + 1, position.getY()));
            } else if (context.getHeading().isEquals(Direction.WEST)) {
                map.setLastPosition(new Position(position.getX() - 1, position.getY()));
            }
        } else if (context.getHeading().isVertical()) {
            if (context.getHeading().equals(Direction.NORTH)) {
                map.setLastPosition(new Position(position.getX(), position.getY() - 1));
            } else if (context.getHeading().isEquals(Direction.SOUTH)) {
                map.setLastPosition(new Position(position.getX(), position.getY() + 1));
            }
        }
    }

    /**
     * set the first postion of the plane
     * @param context data context of simulation
     * @param map object map
     * @throws PositionOutOfMapRange
     */
    public void setFirstPosition(Context context, Map map) throws PositionOutOfMapRange {
        Position position = new Position(0, 0);
        if (context.getHeading().isHorizontal()) {
            position.setX(context.getLastDiscovery().getEchoResponse().getRange());
            if (context.getHeading().isEquals(Direction.NORTH))
                position.setY(0);
            else
                position.setY(map.getHeight() - 1);
        } else {
            position.setY(context.getLastDiscovery().getEchoResponse().getRange());
            if (context.getHeading().isEquals(Direction.EAST))
                position.setX(0);
            else
                position.setY(map.getWidth() - 1);
        }
        try {
            map.initializeTileOcean(position);
        } catch (PositionOutOfMapRange e) {
            throw e;
        }
        map.setLastPosition(position);
    }
}
