package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 11.12.2015
 */
public class UpdaterMap {
    private int axisY;
    private int axisX;
    public UpdaterMap() {
        axisY = 0;
        axisX = 0;
    }

    public void initializeDimensions(Context context, Echo takeAction) {
        if (context.getLastDiscovery().getFound().isEquals(Found.OUT_OF_RANGE)) {
            if (takeAction.getDirection().isVertical() && context.getHeading().isVertical()) {
                if (!context.isHeightDefined()) {
                    context.setHeight(context.getLastDiscovery().getRange() + 1);
                    context.setHeightDefined(true);
                }
            } else if (takeAction.getDirection().isVertical() && !context.getHeading().isVertical()) {
                //if (takeAction.getDirection().equals(Direction.NORTH)) axisY = context.getLastDiscovery().getRange();
                if (context.getHeight() == 0) {
                    context.setHeight(context.getLastDiscovery().getRange());
                } else {
                    context.setHeight(context.getLastDiscovery().getRange() + 1);
                    context.setHeightDefined(true);
                }
            } else if (takeAction.getDirection().isHorizontal() && context.getHeading().isHorizontal()) {
                //axisX = context.getLastDiscovery().getRange();
                if (!context.isWidthDefined()) {
                    context.setWidth(context.getLastDiscovery().getRange() + 1);
                    context.setWidthDefined(true);
                }
            } else if (takeAction.getDirection().isHorizontal() && !context.getHeading().isHorizontal()) {
                //if (takeAction.getDirection().equals(Direction.NORTH)) axisY = context.getLastDiscovery().getRange();
                if (context.getWidth() == 0) {
                    context.setWidth(context.getLastDiscovery().getRange());
                } else {
                    context.setWidth(context.getLastDiscovery().getRange() + 1);
                    context.setWidthDefined(true);
                }
            }
        }
        else if (context.getLastDiscovery().getFound().equals(Found.GROUND)) {
            if (takeAction.getDirection().isVertical() && !context.isHeightDefined()) {
                if (context.getHeight() == 0)
                    context.setHeight(context.getLastDiscovery().getRange() + 2);
                else
                    context.setHeight(context.getLastDiscovery().getRange());
            } else if (takeAction.getDirection().isHorizontal() && !context.isWidthDefined()) {
                if (context.getWidth() == 0)
                    context.setWidth(context.getLastDiscovery().getRange() + 2);
                else
                    context.setWidth(context.getLastDiscovery().getRange());
            }
        }
    }

    public void update(Context context, Map map) {
        if (context.getHeight() != 0 && context.getWidth() != 0) {
            map.initializeMap(context.getHeight(), context.getWidth(), true, true);
        } else if (context.getHeight() != 0 && context.getWidth() == 0) {
            map.initializeMap(context.getHeight(), 1, true, false);
        } else if (context.getHeight() == 0 && context.getWidth() != 0) {
            map.initializeMap(1, context.getWidth(), false, true);
        }
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

    public void setFirstPosition(Context context, Map map) throws PositionOutOfMapaRange {
        Position position = new Position(0, 0);
        map.initializeTileOcean(position);
        map.setLastPosition(position);
    }
}
