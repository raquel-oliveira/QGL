package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyEcho;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateFactory;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 17/12/15.
 */
public class GoToTheCorner extends AerialState {
    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        // if the plane found a space to initialize the dimention
        if (foundFreeZone(context)) {
            stateMediator.setGoToTheCorner(false);
            updateContext(context);
            return AerialStateFactory.buildState(AerialStateType.INITIALIZE);
        } else
            return AerialStateFactory.buildState(AerialStateType.GO_TO_THE_CORNER);
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act = null;

        // make the first and last heading
        if (context.current().getSimpleAction() == null)
            return getHeading(context, stateMediator);


        // after the first heading make the fly until the corner
        if (context.current().getComboAction() == null || context.current().getComboAction().isEmpty()) {
            context.current().setComboAction(new ComboFlyEcho());
            context.current().getComboAction().defineActions(context.getFirstHead());
        }

        // if the actionCombo has a action
        if (context.current().getComboAction() != null) {
            if (context.current().getLastAction() instanceof Fly)
                stateMediator.setRangeToTheCorner(stateMediator.getRangeToTheCorner() - 1);

            act = context.current().getComboAction().get(0);
            context.current().setLastAction(act);
            context.current().getComboAction().remove(0);

            // if the echo found the out_of_range, return the action heading
            if (needLastTurn(context, stateMediator))
                return getHeading(context, stateMediator);
        }
        return act;
    }

    /**
     * Method that will check if it's necessary make the last turn
     * This case happen if the plane found the out_of_range or if
     * it arrive in the map's limit to make a heading
     * @param context data context of the simulation
     * @param stateMediator
     * @return if it's necessary or not
     */
    private static boolean needLastTurn(Context context, StateMediator stateMediator) {
        return (context.getLastDiscovery().getEchoResponse().getFound().equals(Found.OUT_OF_RANGE) &&
                context.getLastDiscovery().getEchoResponse().getDirection().equals(context.getFirstHead())) ||
                    stateMediator.getRangeToGround() == 1;
    }

    /**
     * Return the heading initial and final.
     * @param context data context of the simulation
     * @param stateMediator
     * @return heading action
     */
    private static Action getHeading(Context context, StateMediator stateMediator) {
        if (context.current().getComboAction() == null)
            context.current().setSimpleAction(new Heading(stateMediator.getDirectionToTheCorner()));
        else
            context.current().setSimpleAction(new Heading(context.getFirstHead()));

        context.current().setLastAction(context.current().getSimpleAction());
        return context.current().getSimpleAction();
    }

    /**
     * Method that will check if the plane found a out_of_range to
     * make the turn and take the dimention of the map.
     * @param context data context of the simulation
     * @return if the zone is free to turn and take the dimention
     */
    private static boolean foundFreeZone(Context context) {
        return context.getLastDiscovery().getEchoResponse().getFound().equals(Found.OUT_OF_RANGE) &&
                context.getLastDiscovery().getEchoResponse().getDirection().equals(context.getFirstHead()) &&
                context.current().getLastAction() instanceof Heading;
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
