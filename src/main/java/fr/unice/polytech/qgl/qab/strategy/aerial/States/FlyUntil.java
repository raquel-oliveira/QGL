package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;

/**
 * This State represents the fase when the plane fly until it gets over the land.
 * @version 12.12.2015.
 */
public class FlyUntil extends State {
    public static FlyUntil instance;

    private ComboFlyUntil actionCombo;
    private UpdaterMap updaterMap;

    protected FlyUntil() {
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
    public State getState(Context context, Map map) {
        if (actionCombo != null && actionCombo.isEmpty())
            return State3.getInstance();

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
