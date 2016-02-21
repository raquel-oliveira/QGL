package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 17/12/15.
 */
public class GoToTheCorner extends AerialState {
    private static GoToTheCorner instance;

    private Heading turnCorner;
    private ComboFlyUntil actionCombo;

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
        if (actionCombo != null && actionCombo.isEmpty() && turnCorner != null) {
            stateMediator.setGoToTheCorner(false);
            return Initialize.getInstance();
        } else
            return GoToTheCorner.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        // make the first and last heading
        if (turnCorner == null)
            return getHeading(context, stateMediator);


        // after the first heading make the fly until the corner
        if (actionCombo == null) {
            actionCombo = new ComboFlyUntil();
            actionCombo.defineComboFlyUntil(stateMediator.getRangeToTheCorner() - 2);
        }

        act = actionCombo.get(0);
        lastAction = act;
        actionCombo.remove(0);

        // after the fly until, we set turnCorn null, to make the last heading action
        if (actionCombo.isEmpty())
            turnCorner = null;

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
}
