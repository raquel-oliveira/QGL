package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;

/**
 * @version 12.12.2015.
 */
public class State2 extends State {
    public static State2 instance;

    private ComboFlyUntil actionCombo;
    private Action lastAction;
    private UpdaterMap updaterMap;

    protected State2() {
        lastAction = new Fly();
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
        lastAction = act;
        actionCombo.remove(0);

        return act;
    }
}
