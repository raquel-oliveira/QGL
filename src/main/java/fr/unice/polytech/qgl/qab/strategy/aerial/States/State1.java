package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyEcho;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 11.12.2015.
 */
public class State1 implements IState {
    public static State1 instance;

    private ComboFlyEcho actionCombo;
    private ResponseState response;

    protected State1() {
        actionCombo = new ComboFlyEcho();
        response = null;
    }

    public static State1 getInstance() {
        if (instance == null)
            instance = new State1();
        return instance;
    }

    @Override
    public ResponseState responseState(Context context) {
        if (context.getLastDiscovery().getFound().equals(Found.GROUND)) {
            response.setAction(new Heading(((Echo)response.getAction()).getDirection()));
            response.setStatus(true);
            return response;
        }

        if (actionCombo.isEmpty())
            actionCombo.defineActions(context.getHeading());

        Action act = actionCombo.get(0);
        actionCombo.remove(0);

        response = new ResponseState(act, false);

        return response;
    }
}
