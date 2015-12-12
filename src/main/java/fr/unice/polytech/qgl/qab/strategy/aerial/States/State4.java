package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;

/**
 * @version 12.12.2015.
 */
public class State4 extends State {
    public static State4 instance;

    private ResponseState response;

    protected State4() {
        response = null;
    }

    public static State4 getInstance() {
        if (instance == null)
            instance = new State4();
        return instance;
    }

    @Override
    public State getState(Context context, Map map) {
        return State4.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        return new Stop();
    }
}
