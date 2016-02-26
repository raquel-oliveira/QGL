package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboEchos;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 10.12.2015
 */
public class Initialize extends AerialState {

    //private Combo actionCombo;
    private UpdaterMap updaterMap;

    private Initialize(Context context) {
        updaterMap = new UpdaterMap();
    }

    /**
     * Method to return a instance of Initialize
     * @param context data context of simulation
     * @return a initialize instance
     */
    public static Initialize getInstance(Context context) {
        return new Initialize(context);
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapRange {
        if (context.current().getComboAction() != null) {
            // if it's necessary go to the corner
            needGoToTheCorner(context, stateMediator);

            // initialize the dimention of the map
            if (!stateMediator.shouldGoToTheCorner())
                updaterMap.initializeDimensions(context, map);

            // after made the 3 acho and see if it's necessary go to the corner
            if (context.current().getComboAction().isEmpty() && stateMediator.shouldGoToTheCorner()) {
                updateContext(context);
                return GoToTheCorner.getInstance();
            } else if (context.current().getComboAction().isEmpty() && !stateMediator.shouldGoToTheCorner()) {
                updaterMap.setFirtsPosition(context, map);
                updateContext(context);
                return FindGround.getInstance();
            }
        }
        return Initialize.getInstance(context);
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator mediator) throws IndexOutOfBoundsComboAction {
        // if is the first execution
        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboEchos());
            context.current().getComboAction().defineActions(context.getHeading());
        }

        // return the action of the combo
        Action act = context.current().getComboAction().get(0);
        context.current().setLastAction(act);
        context.current().getComboAction().remove(0);

        return act;
    }

    /**
     * This method will see if it's necessary go to the corner.
     * If the Echo found a ground, the mediator will be changed,
     * and the range and direction to the corner will be save.
     * @param context data's context of the simulation
     * @param mediator
     */
     private static void needGoToTheCorner(Context context, StateMediator mediator) {
        if (mediator.shouldGoToTheCorner() && mediator.getRangeToTheCorner() == 0)
            mediator.setRangeToTheCorner(context.getLastDiscovery().getEchoResponse().getRange());

        if (context.getLastDiscovery() != null && !mediator.shouldGoToTheCorner()
                && context.getLastDiscovery().getEchoResponse().getFound().isEquals(Found.GROUND))
            mediator.setGoToTheCorner(true);

        if (mediator.shouldGoToTheCorner() && context.getLastDiscovery().getEchoResponse().getRange() <= mediator.getRangeToTheCorner()) {
            mediator.setRangeToTheCorner(context.getLastDiscovery().getEchoResponse().getRange());
            mediator.setDirectionToTheCorner(context.getLastDiscovery().getEchoResponse().getDirection());
        }
    }

    /**
     * Method to updata the context
     * @param context
     */
    private void updateContext(Context context) {
        context.current().setComboAction(null);
        context.current().setLastAction(null);
    }
}