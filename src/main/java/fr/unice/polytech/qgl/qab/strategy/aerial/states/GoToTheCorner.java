package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;

/**
 * @version 17/12/15.
 */
public class GoToTheCorner extends AerialState {
    private static GoToTheCorner instance;

    private Heading turnCorner;
    private ComboFlyUntil actionCombo;
    private UpdaterMap updaterMap;

    private GoToTheCorner() {
        super();
        actionCombo = null;
        updaterMap = new UpdaterMap();
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

        if (turnCorner == null && actionCombo == null) {
            turnCorner = new Heading(stateMediator.getDirectionToTheCorner());
            lastAction = turnCorner;
            return turnCorner;
        }

        if (actionCombo == null && turnCorner != null) {
            turnCorner = null;
            actionCombo = new ComboFlyUntil();
            actionCombo.defineComboFlyUntil(stateMediator.getRangeToTheCorner() - 2);
        }

        if (actionCombo != null && actionCombo.isEmpty() && turnCorner == null) {
            turnCorner = new Heading(context.getFirst_head());
            lastAction = turnCorner;
            return turnCorner;
        }

        act = actionCombo.get(0);
        lastAction = act;
        actionCombo.remove(0);

        return act;
    }
}
