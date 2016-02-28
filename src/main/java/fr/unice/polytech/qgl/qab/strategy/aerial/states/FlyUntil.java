package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.UpdaterMap;

/**
 * This AerialState represents the phase when the plane fly until it gets over the land.
 * @version 12/12/2015.
 */
public class FlyUntil extends AerialState {

    private UpdaterMap updaterMap;

    private FlyUntil() {
        updaterMap = new UpdaterMap();
    }

    public static FlyUntil getInstance() {
        return new FlyUntil();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (context.current().getComboAction().isEmpty()) {
            updateContext(context);
            return ScanTheGround.getInstance();
        }
        return FlyUntil.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        if (context.current().getComboAction() == null)
            context.current().setComboAction(new ComboFlyUntil());

        // if action is empty, set the combo fly
        if (context.current().getComboAction().isEmpty())
            context.current().getComboAction().defineActions(stateMediator.getRangeToGround() + 1);

        act = context.current().getComboAction().get(0);
        context.current().getComboAction().remove(0);
        updaterMap.updateLastPositionFly(context, map);

        return act;
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
