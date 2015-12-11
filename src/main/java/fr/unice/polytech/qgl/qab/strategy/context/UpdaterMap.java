package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 11.12.2015
 */
public class UpdaterMap {
    public UpdaterMap() {}

    public void initializeDimensions(Context context, Echo takeAction) {
        if (context.getLastDiscovery().getFound().equals(Found.OUT_OF_RANGE)) {
            if (takeAction.getDirection().isVertical() && context.getHeading().isVertical()) {
                if (!context.isHeightDefined()) {
                    context.setHeight(context.getLastDiscovery().getRange() + 1);
                    context.setHeightDefined(true);
                }
            } else if (takeAction.getDirection().isVertical() && !context.getHeading().isVertical()) {
                if (context.getHeight() == 0) {
                    context.setHeight(context.getLastDiscovery().getRange());
                } else {
                    context.setHeight(context.getLastDiscovery().getRange() + 1);
                    context.setHeightDefined(true);
                }
            } else if (takeAction.getDirection().isHorizontal() && context.getHeading().isHorizontal()) {
                if (!context.isWidthDefined()) {
                    context.setWidth(context.getLastDiscovery().getRange() + 1);
                    context.setWidthDefined(true);
                }
            } else if (takeAction.getDirection().isHorizontal() && !context.getHeading().isHorizontal()) {
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
}
