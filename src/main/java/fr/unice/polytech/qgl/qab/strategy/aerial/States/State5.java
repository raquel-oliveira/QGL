package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 13.12.2015.
 */
public class State5 extends State {
    public static State5 instance;

    protected State5() { }

    public static State5 getInstance() {
        if (instance == null)
            instance = new State5();
        return instance;
    }

    @Override
    public State getState(Context context, Map map) {
        return State5.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        return new Stop();
    }
}
