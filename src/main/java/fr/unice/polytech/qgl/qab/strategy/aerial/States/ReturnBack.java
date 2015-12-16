package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboReturn;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 12.12.2015.
 */
public class ReturnBack extends AerialState {
    public static ReturnBack instance;

    private ComboReturn actionCombo;
    private int indexAction;

    protected ReturnBack() {
        super();
        actionCombo = null;
        indexAction = 0;
    }

    public static ReturnBack getInstance() {
        if (instance == null)
            instance = new ReturnBack();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map) {
        if (lastAction instanceof Echo) {
            if (context.getLastDiscovery().getFound().equals(Found.OUT_OF_RANGE))
                return MakeLand.getInstance();
            return ScanTheGround.getInstance();
        }
        return ReturnBack.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        Action act = null;

        if (actionCombo == null) {
            actionCombo = new ComboReturn();
            actionCombo.defineHeading(context.getHeading(), map, context.getFirst_head());
        }

        if (actionCombo != null) {
            act = actionCombo.get(indexAction);
            lastAction = act;
            if (lastAction instanceof Heading) {
                Direction dir = ((Heading) lastAction).getDirection();
                context.setHeading(dir);
            }
            indexAction++;
        }

        if (indexAction == 6) indexAction = 0;

        return act;
    }
}
