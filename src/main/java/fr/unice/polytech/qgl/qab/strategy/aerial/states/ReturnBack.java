package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboReturn;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 12.12.2015.
 */
public class ReturnBack extends AerialState {
    private static ReturnBack instance;

    private ComboReturn actionCombo;
    private ComboFlyUntil comboFlyUntil;
    private int indexAction;

    private ReturnBack() {
        super();
        actionCombo = null;
        indexAction = 0;
        comboFlyUntil = null;
    }

    public static ReturnBack getInstance() {
        if (instance == null)
            instance = new ReturnBack();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if ((lastAction instanceof Fly && !stateMediator.shouldFlyUntilGround()) ||
                (lastAction instanceof Echo && context.getLastDiscovery().getRange() == 0)) {
            return ScanTheGround.getInstance();
        }
        return ReturnBack.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act = null;

        if (lastAction instanceof Echo) {
            if (context.getLastDiscovery().getFound().isEquals(Found.OUT_OF_RANGE))
                return new Stop();
            else {
                comboFlyUntil = new ComboFlyUntil();
                comboFlyUntil.defineComboFlyUntil(context.getLastDiscovery().getRange());
                stateMediator.shouldFlyUntilGround(true);
            }
        }

        if (comboFlyUntil != null && !comboFlyUntil.isEmpty()) {
            act = comboFlyUntil.get(0);
            lastAction = act;
            comboFlyUntil.remove(0);
            if (comboFlyUntil.isEmpty())
                stateMediator.shouldFlyUntilGround(false);
            return act;
        }

        if (actionCombo == null) {
            actionCombo = new ComboReturn();
            actionCombo.defineHeading(context.getHeading(), context.getFirstHead());
        }

        if (actionCombo != null) {
            act = actionCombo.get(indexAction);
            lastAction = act;
            if (lastAction instanceof Heading) {
                Direction dir = (lastAction).getDirection();
                context.setHeading(dir);
            }
            indexAction++;
        }

        if (indexAction == 6)
            indexAction = 0;

        return act;
    }
}
