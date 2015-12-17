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

    private ComboFlyUntil actionCombo;
    private UpdaterMap updaterMap;

    private FlyUntil() {
        super();
        actionCombo = null;
        updaterMap = new UpdaterMap();
    }

    public static FlyUntil getInstance() {
        if (instance == null)
            instance = new FlyUntil();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map) {
        if (actionCombo != null && actionCombo.isEmpty())
            return ScanTheGround.getInstance();

        updateContext(context, map);
        return FlyUntil.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map) {
        Action act;

        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyUntil();
            actionCombo.defineComboFlyUntil(context.getLastDiscovery().getRange() + 1);
        }

        act = actionCombo.get(0);
        actionCombo.remove(0);

        return act;
    }

    private void updateContext(Context context, Map map) {
        updaterMap.updateLastPositionFly(context, map);
    }
}
