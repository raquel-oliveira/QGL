package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboEchos;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;

/**
 * @version 10.12.2015
 */
public class State0 implements IState {
    public static State0 instance;

    private ComboEchos actionCombo;
    private ResponseState response;

    protected State0() {
        actionCombo = new ComboEchos();
        response = null;
    }

    public static State0 getInstance() {
        if (instance == null)
            instance = new State0();
        return instance;
    }

    @Override
    public ResponseState responseState(Context context) {
        if (actionCombo.isEmpty())
            actionCombo.defineComboEchos(context.getHeading());

        Action act = actionCombo.get(0);
        actionCombo.remove(0);

        if (actionCombo.isEmpty())
            response = new ResponseState(act, true);
        else response = new ResponseState(act, false);

        return response;
    }
}
