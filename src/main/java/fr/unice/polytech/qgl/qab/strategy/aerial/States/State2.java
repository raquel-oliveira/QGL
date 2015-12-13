package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;

/**
 * This State represents the fase when the plane fly until it gets over the land.
 * @version 12.12.2015.
 */
public class State2 extends State {
    public static State2 instance;

    private ComboFlyUntil actionCombo;
    private UpdaterMap updaterMap;

    protected State2() {
        super();
        actionCombo = null;
        updaterMap = new UpdaterMap();
    }

    public static State2 getInstance() {
        if (instance == null)
            instance = new State2();
        return instance;
    }

    @Override
    public State getState(Context context, Map map) {
        if (actionCombo != null && actionCombo.isEmpty())
            return State3.getInstance();
        updateContext(context, map);
        return State2.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map) {
        Action act = null;

        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyUntil();
            actionCombo.defineComboFlyUntil(context.getLastDiscovery().getRange());
        }

        act = actionCombo.get(0);
        actionCombo.remove(0);

        return act;
    }

    private void updateContext(Context context, Map map) {
        updaterMap.updateLastPositionFly(context, map);
    }
}
