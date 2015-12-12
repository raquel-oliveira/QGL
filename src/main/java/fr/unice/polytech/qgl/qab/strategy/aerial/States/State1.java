package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyEcho;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 11.12.2015.
 */
public class State1 extends State {
    public static State1 instance;

    private ComboFlyEcho actionCombo;
    private Action lastAction;
    private UpdaterMap updaterMap;

    protected State1() {
        actionCombo = null;
        lastAction = new Fly();
        updaterMap = new UpdaterMap();
    }

    public static State1 getInstance() {
        if (instance == null)
            instance = new State1();
        return instance;
    }

    @Override
    public State getState(Context context, Map map) {
        if (actionCombo != null && actionCombo.isEmpty())
            updateContext(context, map);

        if (lastAction instanceof Heading)
            return State2.getInstance();

        return State1.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        Action act = null;
        if (context.getLastDiscovery().getFound().equals(Found.GROUND) && lastAction instanceof Echo) {
            act = new Heading(((Echo)lastAction).getDirection());
            lastAction = act;
            return act;
        }

        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyEcho();
            actionCombo.defineActions(context.getHeading());
        }

        act = actionCombo.get(0);

        if (act instanceof Echo) {
            lastAction = (Echo) act;
        } else if (act instanceof Fly) {
            lastAction = (Fly) act;
            updateContext(context, map);
        }

        actionCombo.remove(0);

        return act;
    }

    private void updateContext(Context context, Map map) {
        updaterMap.updateLastPositionFly(context, map);
    }
}
