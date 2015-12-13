package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyScan;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboReturn;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 12.12.2015.
 */
public class State4 extends State {
    public static State4 instance;

    private ComboReturn actionCombo;

    protected State4() {
        super();
        actionCombo = null;
        lastAction = null;
    }

    public static State4 getInstance() {
        if (instance == null)
            instance = new State4();
        return instance;
    }

    @Override
    public State getState(Context context, Map map) {
        if (lastAction instanceof Echo) {
            if (context.getLastDiscovery().getFound().equals(Found.OUT_OF_RANGE))
                return State5.getInstance();
            return State3.getInstance();
        }
        return State4.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        Action act = null;

        if (actionCombo == null) {
            actionCombo = new ComboReturn();
            actionCombo.defineHeading(context.getHeading(), map, context.getFirst_head());
        }

        if (actionCombo != null && actionCombo.isEmpty()) {
            act = new Echo(context.getHeading());
            lastAction = act;
            return act;
        }

        if (actionCombo != null && !actionCombo.isEmpty()) {
            act = actionCombo.get(0);
            lastAction = act;
            Direction dir = ((Heading) lastAction).getDirection();
            context.setHeading(dir);
            actionCombo.remove(0);
        }

        return act;
    }
}
