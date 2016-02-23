package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;
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

    public static ReturnBack getInstance() {
        return new ReturnBack();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        // action echo
        if (context.getLastAction() instanceof Echo) {
            if (context.getLastDiscovery().getEchoResponse().getFound().equals(Found.GROUND)) {
                if (context.getLastDiscovery().getEchoResponse().getRange() >= 1) {
                    context.setComboAction(null);
                    stateMediator.setRangeToGround(context.getLastDiscovery().getEchoResponse().getRange() + 1);
                    return FlyUntil.getInstance();
                } else {
                    context.setLastAction(null);
                    context.setComboAction(null);
                    return ScanTheGround.getInstance();
                }
            } else {
                return StopSimulation.getInstance();
            }
        }

        return ReturnBack.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act = null;

        if (context.getComboReturnBack() == null) {
            context.setComboReturnBack(new ComboReturn());
            context.getComboReturnBack().defineActions(context.getHeading(), context.getFirstHead());
        }

        act = context.getComboReturnBack().get(context.getIndexAction());
        context.setLastAction(act);
        if (context.getLastAction() instanceof Heading)
            context.setHeading(act.getDirection());
        context.incrementIndexAction();

        if (context.getIndexAction() == 6)
            context.setIndexAction(0);


        return act;
    }
}
