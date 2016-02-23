package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyScan;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * This AerialState represents the phase when the plane fly until it gets over the land.
 * @version 12/12/2015.
 */
public class FlyUntil extends AerialState {

    public static FlyUntil getInstance() {
        return new FlyUntil();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (context.action().getComboAction().isEmpty()) {
            context.action().setComboAction(null);
            context.action().setLastAction(new Fly());
            return ScanTheGround.getInstance();
        }
        return FlyUntil.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        if (context.action().getComboAction() == null)
            context.action().setComboAction(new ComboFlyUntil());

        // if action is empty, set the combo fly
        if (context.action().getComboAction().isEmpty())
            context.action().getComboAction().defineActions(stateMediator.getRangeToGround());

        act = context.action().getComboAction().get(0);
        context.action().getComboAction().remove(0);
        return act;
    }

}
