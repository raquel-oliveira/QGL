package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * This AerialState represents the phase when the plane fly until it gets over the land.
 * @version 12.12.2015.
 */
public class FlyUntil extends AerialState {
    private static FlyUntil instance;

    private ComboFlyUntil actionCombo;

    private FlyUntil() {
        super();
        actionCombo = new ComboFlyUntil();
    }

    public static FlyUntil getInstance() {
        if (instance == null)
            instance = new FlyUntil();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (actionCombo.isEmpty()) {
            actionCombo = new ComboFlyUntil();
            return ScanTheGround.getInstance();
        }
        return FlyUntil.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        if (actionCombo.isEmpty())
            actionCombo.defineComboFlyUntil(stateMediator.getRangeToGround());

        act = actionCombo.get(0);
        actionCombo.remove(0);
        return act;
    }

}
