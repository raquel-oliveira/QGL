package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboEchos;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 10.12.2015
 */
public class State0 extends State {
    public static State0 instance;

    private ComboEchos actionCombo;
    private UpdaterMap updaterMap;
    private int rangeToFlyUntil;

    protected State0() {
        super();
        updaterMap = new UpdaterMap();
        rangeToFlyUntil = 0;
    }

    public static State0 getInstance() {
        if (instance == null)
            instance = new State0();
        return instance;
    }

    @Override
    public State getState(Context context, Map map) {
        if (actionCombo != null && actionCombo.isEmpty()) {
            updateContext(context, map);
            if (rangeToFlyUntil > 0) {
                context.getLastDiscovery().setRange(rangeToFlyUntil);
                return State2.getInstance();
            }
            return State1.getInstance();
        }
        return State0.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map) {
        if (actionCombo == null) {
            actionCombo = new ComboEchos();
            actionCombo.defineComboEchos(context.getHeading());
        }

        Action act = actionCombo.get(0);
        lastAction = (Echo) act;
        actionCombo.remove(0);


        if (context.getLastDiscovery() != null) {
            updaterMap.initializeDimensions(context, (Echo) act);
            if (context.getLastDiscovery().getFound().equals(Found.GROUND)) {
                rangeToFlyUntil = context.getLastDiscovery().getRange();
            }
        }

        return act;
    }

    private void updateContext(Context context, Map map) {
        updaterMap.initializeDimensions(context, (Echo)lastAction);
        updaterMap.update(context, map);
        updaterMap.setFirstPosition(context, map);
    }
}