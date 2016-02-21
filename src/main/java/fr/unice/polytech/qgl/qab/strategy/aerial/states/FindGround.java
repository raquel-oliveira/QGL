package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyEcho;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 11.12.2015.
 */
public class FindGround extends AerialState {
    private static FindGround instance;

    private ComboFlyEcho actionCombo;

    private FindGround() {
        super();
        actionCombo = null;
        this.lastAction = new Fly();
    }

    /**
     * get the instance of FindGround
     * @return
     */
    public static FindGround getInstance() {
        if (instance == null)
            instance = new FindGround();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (lastAction instanceof Heading)
            return FlyUntil.getInstance();

        return FindGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;
        if (context.getLastDiscovery().getEchoResponse().getFound().equals(Found.GROUND) && lastAction instanceof Echo) {
            Direction dir = (lastAction).getDirection();
            act = new Heading(dir);
            context.setHeading(dir);
            stateMediator.setRangeToGround(context.getLastDiscovery().getEchoResponse().getRange());
            lastAction = act;
            return act;
        }

        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyEcho();
            actionCombo.defineActions(choiceDirectionEcho(context, map));
        }

        act = actionCombo.get(0);
        lastAction = act;
        actionCombo.remove(0);

        return act;
    }

    /**
     * Choice the direction to make echo
     * @param context data context of the simulation
     * @param map of the simulation
     * @return direction
     */
    private Direction choiceDirectionEcho(Context context, Map map) {
        if (context.getHeading().isVertical()) {
            if (map.getLastPosition().getX() > map.getWidth()/2)
                return Direction.WEST;
            else
                return Direction.EAST;
        } else {
            if (map.getLastPosition().getY() < map.getHeight()/2)
                return Direction.SOUTH;
            else
                return Direction.NORTH;
        }
    }
}