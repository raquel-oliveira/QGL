package fr.unice.polytech.qgl.qab.strategy.context.utils;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
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
     * after each heading, it's necessary update the position of the plane
     * @param context data context of simulation
     * @param map object map
     */
    public void updateLastPositionHeading(Context context, Map map) {
        Position position = map.getLastPosition();

        updateLastPositionFly(context, map);
        context.setHeading(context.current().getLastAction().getDirection());
        updateLastPositionFly(context, map);
    }

    /**
     * Set the first position in the map
     * @param context data context of the simulation
     * @param map of the simulation
     * @throws PositionOutOfMapRange
     */
    public void setFirtsPosition(Context context, Map map) throws PositionOutOfMapRange {
        if (context.getHeading().isVertical()) {
            if (context.getHeading().equals(Direction.NORTH))
                map.setLastPosition(new Position(context.getLastDiscovery().getEchoResponse().getRange(), map.getHeight() - 1));
            else
                map.setLastPosition(new Position(context.getLastDiscovery().getEchoResponse().getRange(), 0));
        } else  {
            if (context.getHeading().equals(Direction.EAST))
                map.setLastPosition(new Position(0, context.getLastDiscovery().getEchoResponse().getRange()));
            else
                map.setLastPosition(new Position(map.getWidth() - 1, context.getLastDiscovery().getEchoResponse().getRange()));
        }
        map.initializeTile(map.getLastPosition(), TileType.OCEAN);
    }

    public void setBiomeTile(Context context, Map map) {
        map.addBiome(map.getLastPosition(), context.getLastDiscovery().getScanResponse().getBiomes());
    }
}