package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;

/**
 * This AerialState represents the phase when the plane fly until it gets over the land.
 * @version 12.12.2015.
 */
public class FlyUntil extends AerialState {
    private static FlyUntil instance;

    private StateMediator stateMediator;
    private ComboFlyUntil actionCombo;
    private UpdaterMap updaterMap;

    private FlyUntil() {
        super();
        actionCombo = new ComboFlyUntil();
        updaterMap = new UpdaterMap();
    }

    public static FlyUntil getInstance() {
        if (instance == null)
            instance = new FlyUntil();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (actionCombo.isEmpty())
            return ScanTheGround.getInstance();
        return FlyUntil.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map, StateMediator stateMediator) {
        Action act;

        if (actionCombo.isEmpty())
            actionCombo.defineComboFlyUntil(stateMediator.getRangeToGround() + 1);

        act = actionCombo.get(0);
        actionCombo.remove(0);
        return act;
    }

    private void updateContext(Context context, Map map) {
        updaterMap.updateLastPositionFly(context, map);
    }
}
