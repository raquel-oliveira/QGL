package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyEcho;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 17/12/15.
 */
public class GoToTheCorner extends AerialState {
    private static GoToTheCorner instance;

    private Heading turnCorner;
    private ComboFlyEcho actionCombo;

    private GoToTheCorner() {
        super();
        actionCombo = null;
        turnCorner = null;
    }

    public static GoToTheCorner getInstance() {
        if (instance == null)
            instance = new GoToTheCorner();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (foundFreeZone(context)) {
            stateMediator.setGoToTheCorner(false);
            return Initialize.getInstance();
        } else
            return GoToTheCorner.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act = null;

        // make the first and last heading
        if (turnCorner == null)
            return getHeading(context, stateMediator);


        // after the first heading make the fly until the corner
        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyEcho();
            actionCombo.defineActions(context.getFirstHead());
        }

        // if the actionCombo has a action
        if (actionCombo != null) {
            act = actionCombo.get(0);
            lastAction = act;
            actionCombo.remove(0);

            // if the echo found the out_of_range, return the action heading
            if (context.getLastDiscovery().getEchoResponse().getFound().equals(Found.OUT_OF_RANGE) &&
                    context.getLastDiscovery().getEchoResponse().getDirection().equals(context.getFirstHead()))
                return getHeading(context, stateMediator);
        }

        return act;
    }

    /**
     * Return the heading initial and final.
     * @param context data context of the simulation
     * @param stateMediator
     * @return heading action
     */
    private Action getHeading(Context context, StateMediator stateMediator) {
        if (actionCombo == null) {
            turnCorner = new Heading(stateMediator.getDirectionToTheCorner());
            lastAction = turnCorner;
            return turnCorner;
        } else {
            turnCorner = new Heading(context.getFirstHead());
            lastAction = turnCorner;
            return turnCorner;
        }
    }

    /**
     * Method that will check if the plane found a out_of_range to
     * make the turn and take the dimention of the map.
     * @param context data context of the simulation
     * @return if the zone is free to turn and take the dimention
     */
    private boolean foundFreeZone(Context context) {
        return context.getLastDiscovery().getEchoResponse().getFound().equals(Found.OUT_OF_RANGE) &&
                context.getLastDiscovery().getEchoResponse().getDirection().equals(context.getFirstHead()) &&
                lastAction instanceof Heading;
    }
}
