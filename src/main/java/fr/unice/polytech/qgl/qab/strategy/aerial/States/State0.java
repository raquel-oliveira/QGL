package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboEchos;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;

/**
 * @version 10.12.2015
 */
public class State0 extends State {
    public static State0 instance;

    private ComboEchos actionCombo;
    private Echo lastAction;
    private UpdaterMap updaterMap;

    protected State0() {
        actionCombo = null;
        updaterMap = new UpdaterMap();
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

        return act;
    }

    private void updateContext(Context context, Map map) {
        updaterMap.initializeDimensions(context, lastAction);
        updaterMap.update(context, map);
        updaterMap.setFirstPosition(context, map);
    }
}
