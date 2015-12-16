package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyEcho;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 11.12.2015.
 */
public class FindGround extends AerialState {
    public static FindGround instance;

    private ComboFlyEcho actionCombo;
    private UpdaterMap updaterMap;

    protected FindGround() {
        super();
        actionCombo = null;
        this.lastAction = new Fly();
        updaterMap = new UpdaterMap();
    }

    public static FindGround getInstance() {
        if (instance == null)
            instance = new FindGround();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map) {
        if (actionCombo != null && actionCombo.isEmpty())
            updateContext(context, map);

        if (lastAction instanceof Heading)
            return FlyUntil.getInstance();

        return FindGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        Action act;
        if (context.getLastDiscovery().getFound().equals(Found.GROUND) && lastAction instanceof Echo) {
            Direction dir = ((Echo)lastAction).getDirection();
            act = new Heading(dir);
            context.setHeading(dir);
            lastAction = act;
            return act;
        }

        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyEcho();
            actionCombo.defineActions(context.getHeading());
        }

        act = actionCombo.get(0);

        if (act instanceof Echo) {
            lastAction = act;
        } else if (act instanceof Fly) {
            lastAction = act;
            updateContext(context, map);
        }

        actionCombo.remove(0);

        return act;
    }

    private void updateContext(Context context, Map map) {
        updaterMap.updateLastPositionFly(context, map);
    }
}