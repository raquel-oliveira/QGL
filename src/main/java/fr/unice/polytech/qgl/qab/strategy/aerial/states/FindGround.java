package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyEcho;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateFactory;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 11.12.2015.
 */
public class FindGround extends AerialState {
    private UpdaterMap updaterMap;

    /**
     * FindGround's constructor.
     */
    public FindGround() {
        this.updaterMap = new UpdaterMap();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (context.current().getLastAction() instanceof Heading) {
            updateContext(context);
            return AerialStateFactory.buildState(AerialStateType.FLY_UNTIL);
        }
        return AerialStateFactory.buildState(AerialStateType.FIND_GROUND);
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        // if the bot made a echo and found a ground, so it's necessary make a heading
        if (context.getLastDiscovery().getEchoResponse().getFound().equals(Found.GROUND) && context.current().getLastAction() instanceof Echo) {
            Direction dir = context.current().getLastAction().getDirection();
            act = new Heading(dir);
            stateMediator.setRangeToGround(context.getLastDiscovery().getEchoResponse().getRange());
            context.current().setLastAction(act);
            updaterMap.updateLastPositionHeading(context, map);
            return act;
        }

        // set the combo fly + echo to find the ground
        if (context.current().getComboAction() == null || context.current().getComboAction() .isEmpty()) {
            context.current().setComboAction(new ComboFlyEcho());
            context.current().getComboAction().defineActions(choiceDirectionEcho(context, map));
        }

        // take the action of the combo
        act = context.current().getComboAction().get(0);
        context.current().setLastAction(act);
        context.current().getComboAction().remove(0);
        if (context.current().getLastAction() instanceof Fly)
            updaterMap.updateLastPositionFly(context, map);

        return act;
    }

    /**
     * Choice the direction to make echo
     * @param context data context of the simulation
     * @param map of the simulation
     * @return direction
     */
    private static Direction choiceDirectionEcho(Context context, Map map) {
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


    /**
     * Method to updata the context
     * @param context
     */
    private static void updateContext(Context context) {
        context.current().setComboAction(null);
        context.current().setLastAction(null);
    }
}