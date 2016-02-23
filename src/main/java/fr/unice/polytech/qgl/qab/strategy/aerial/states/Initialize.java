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
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 10.12.2015
 */
public class Initialize extends AerialState {

    //private Combo actionCombo;
    private UpdaterMap updaterMap;

    private Initialize() {
        updaterMap = new UpdaterMap();
    }

    public static Initialize getInstance() {
        return new Initialize();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapRange {
        if (context.getComboAction() != null) {
            // if it's necessary go to the corner
            needGoToTheCorner(context, stateMediator);

            // initialize the dimention of the map
            if (!stateMediator.shouldGoToTheCorner())
                updaterMap.initializeDimensions(context, map);

            // after made the 3 acho and see if it's necessary go to the corner
            if (context.getComboAction().isEmpty() && stateMediator.shouldGoToTheCorner()) {
                context.setComboAction(null);
                return GoToTheCorner.getInstance();
            } else if (context.getComboAction().isEmpty() && !stateMediator.shouldGoToTheCorner()) {
                setFirtsPosition(context, map);
                context.setComboAction(null);
                context.setSimpleAction(new Fly());
                return FindGround.getInstance();
            }
        }
        return Initialize.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator mediator) throws IndexOutOfBoundsComboAction {
        // if is the first execution
        if (context.getComboAction() == null) {
            context.setComboAction(new ComboEchos());
            ((ComboEchos)context.getComboAction()).defineComboEchos(context.getHeading());
        }

        // return the action of the combo
        Action act = context.getComboAction().get(0);
        context.setLastAction(act);
        context.getComboAction().remove(0);
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
     * Set the first position in the map
     * @param context data context of the simulation
     * @param map of the simulation
     * @throws PositionOutOfMapRange
     */
     private static void setFirtsPosition(Context context, Map map) throws PositionOutOfMapRange {
        if (context.getHeading().isVertical()) {
            if (context.getHeading().equals(Direction.NORTH))
                map.setLastPosition(new Position(context.getLastDiscovery().getEchoResponse().getRange(), map.getHeight() - 1));
            else
                map.setLastPosition(new Position(context.getLastDiscovery().getEchoResponse().getRange(), 0));
        } else  {
            if (context.getHeading().equals(Direction.EAST))
                map.setLastPosition(new Position(0, context.getLastDiscovery().getEchoResponse().getRange()));
            else
                map.setLastPosition(new Position(map.getWidth() - 1, context.getLastDiscovery().getEchoResponse().getRange()));
        }
        map.initializeTileOcean(map.getLastPosition());
    }
}