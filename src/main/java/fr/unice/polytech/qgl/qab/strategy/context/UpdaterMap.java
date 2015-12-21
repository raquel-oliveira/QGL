package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

/**
 * @version 11/12/15
 */
public class UpdaterMap {
    public UpdaterMap() {}

    public void initializeDimensions(Context context, Map map) {
        if (context.getLastDiscovery().getDirection().isEquals(context.getFirst_head())) {
            if (context.getFirst_head().isHorizontal()) {
                setWidth(map, context.getLastDiscovery().getRange() + 1, true);
            } else {
                setHeight(map, context.getLastDiscovery().getRange() + 1, true);
            }
        } else {
            if (context.getFirst_head().isVertical()) {
                if (map.getWidth() >= 0) {
                    setWidth(map, context.getLastDiscovery().getRange() + 1, true);
                } else {
                    setWidth(map, context.getLastDiscovery().getRange() + 1, false);
                }
            } else {
                if (map.getHeight() >= 0) {
                    setHeight(map, context.getLastDiscovery().getRange() + 1, true);
                } else {
                    setHeight(map, context.getLastDiscovery().getRange() + 1, false);
                }
            }
        }
    }

    public void setHeight(Map map, int value, boolean defined) {
        map.initializeHeightMap(value, defined);
    }

    public void setWidth(Map map, int value, boolean defined) {
        map.initializeWidthMap(value, defined);
    }

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
                map.setLastPosition(new Position(position.getX() - 1, position.getY() + 1));
            }
        }
    }

    public void setFirstPosition(Context context, Map map) throws PositionOutOfMapRange {
        Position position = new Position(0, 0);
        if (context.getHeading().isHorizontal()) {
            position.setX(context.getLastDiscovery().getRange());
            if (context.getHeading().isEquals(Direction.NORTH)) position.setY(0);
            else position.setY(map.getHeight() - 1);
        } else {
            position.setY(context.getLastDiscovery().getRange());
            if (context.getHeading().isEquals(Direction.EAST)) position.setX(0);
            else position.setY(map.getWidth() - 1);
        }
        map.initializeTileOcean(position);
        map.setLastPosition(position);
    }
}
