package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyEcho;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;

/**
 * @version 12.12.2015.
 */
public class State2 implements IState {
    public static State2 instance;

    private ResponseState response;

    protected State2() {
        response = null;
    }

    public static State2 getInstance() {
        if (instance == null)
            instance = new State2();
        return instance;
    }

    public ResponseState responseState(Context context) {
        response = new ResponseState(new Stop(), true);
        return response;
    }
}
