package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboReturn;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
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
    private StateMediator stateMediator;

    private ReturnBack() {
        super();
        actionCombo = null;
        indexAction = 0;
        comboFlyUntil = null;
        stateMediator = StateMediator.getInstance();
    }

    public static ReturnBack getInstance() {
        if (instance == null)
            instance = new ReturnBack();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (lastAction instanceof Fly && !stateMediator.shouldFlyUntilGround()) {
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
                comboFlyUntil.defineComboFlyUntil(context.getLastDiscovery().getRange() + 1);
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
                Direction dir = ((Heading) lastAction).getDirection();
                context.setHeading(dir);
            }
            indexAction++;
        }

        if (indexAction == 6) indexAction = 0;

        return act;
    }
}
