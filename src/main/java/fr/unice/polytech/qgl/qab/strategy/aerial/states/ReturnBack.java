package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboReturn;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateFactory;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * This AerialState represents the phase when the plane need return back to the ground.
 * After fly in the ocean and see that there is not ground in the same direction,
 * the bot will return back.
 * @version 12.12.2015.
 */
public class ReturnBack extends AerialState {

    private UpdaterMap updaterMap;

    /**
     * ReturnBack's constructor
     */
    public ReturnBack() {
        updaterMap = new UpdaterMap();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        // action echo
        if (context.current().getLastAction() instanceof Echo) {
            if (context.getLastDiscovery().getEchoResponse().getFound().equals(Found.GROUND)) {
                if (context.getLastDiscovery().getEchoResponse().getRange() >= 1) {
                    updateContext(context);
                    stateMediator.setRangeToGround(context.getLastDiscovery().getEchoResponse().getRange() + 1);
                    return AerialStateFactory.buildState(AerialStateType.FLY_UNTIL);
                } else {
                    updateContext(context);
                    return AerialStateFactory.buildState(AerialStateType.SCAN_THE_GROUND);
                }
            } else {
                return AerialStateFactory.buildState(AerialStateType.LAND_IN_GROUND);
            }
        }

        return new ReturnBack();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        if (context.current().getComboReturnBack() == null) {
            context.current().setComboReturnBack(new ComboReturn());
            context.current().getComboReturnBack().defineActions(context.getHeading(), context.getFirstHead());
        }

        act = context.current().getComboReturnBack().get(context.current().getIndexAction());
        context.current().setLastAction(act);
        if (context.current().getLastAction() instanceof Heading)
            updaterMap.updateLastPositionHeading(context, map);

        context.current().incrementIndexAction();

        if (context.current().getIndexAction() == 6)
            context.current().setIndexAction(0);

        return act;
    }

    /**
     * Method to updata the context
     * @param context
     */
    private static void updateContext(Context context) {
        context.current().setComboAction(null);
        context.current().setLastAction(null);
    }
}
