package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
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

    public static FindGround getInstance() {
        return new FindGround();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (context.getSimpleAction() instanceof Heading) {
            context.setComboAction(null);
            return FlyUntil.getInstance();
        }

        return FindGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        // if the bot made a echo and found a ground, so it's necessary make a heading
        if (context.getLastDiscovery().getEchoResponse().getFound().equals(Found.GROUND) && context.getSimpleAction() instanceof Echo) {
            Direction dir = context.getSimpleAction().getDirection();
            act = new Heading(dir);
            context.setHeading(dir);
            stateMediator.setRangeToGround(context.getLastDiscovery().getEchoResponse().getRange());
            context.setSimpleAction(act);
            return act;
        }

        // set the combo fly + echo to find the ground
        if (context.getComboAction()  == null || context.getComboAction() .isEmpty()) {
            context.setComboAction(new ComboFlyEcho());
            context.getComboAction() .defineActions(choiceDirectionEcho(context, map));
        }

        // take the action of the combo
        act = context.getComboAction() .get(0);
        context.setSimpleAction(act);
        context.getComboAction() .remove(0);

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
            if (map.getLastPosition().getY() > map.getHeight()/2)
                return Direction.SOUTH;
            else
                return Direction.NORTH;
        }
    }
}