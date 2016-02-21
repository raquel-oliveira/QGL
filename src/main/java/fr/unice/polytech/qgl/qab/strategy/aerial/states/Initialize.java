package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboEchos;
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
    private static Initialize instance;

    private Combo actionCombo;
    private UpdaterMap updaterMap;

    private Initialize() {
        super();
        updaterMap = new UpdaterMap();
        actionCombo = null;
    }

    public static Initialize getInstance() {
        if (instance == null)
            instance = new Initialize();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapRange {
        if (actionCombo != null) {
            // if it's necessary go to the corner
            needGoToTheCorner(context, stateMediator);

            if (!stateMediator.shouldGoToTheCorner())
                updaterMap.initializeDimensions(context, map);

            if (actionCombo.isEmpty() && stateMediator.shouldGoToTheCorner()) {
                actionCombo = null;
                return GoToTheCorner.getInstance();
            } else if (actionCombo.isEmpty() && !stateMediator.shouldGoToTheCorner()) {
                setFirtsPosition(context, map);
                return FindGround.getInstance();
            }
        }
        return Initialize.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator mediator) throws IndexOutOfBoundsComboAction {
        // if is the first execution
        if (actionCombo == null) {
            actionCombo = new ComboEchos();
            ((ComboEchos)actionCombo).defineComboEchos(context.getHeading());
        }

        Action act = actionCombo.get(0);
        lastAction = act;
        actionCombo.remove(0);
        return act;
    }

    /**
     * This method will see if it's necessary go to the corner.
     * If the Echo found a ground, the mediator will be changed,
     * and the range and direction to the corner will be save.
     * @param context data's context of the simulation
     * @param mediator
     */
    private void needGoToTheCorner(Context context, StateMediator mediator) {
        if (mediator.shouldGoToTheCorner() && mediator.getRangeToTheCorner() == 0)
            mediator.setRangeToTheCorner(context.getLastDiscovery().getEchoResponse().getRange());

        if (context.getLastDiscovery() != null && !mediator.shouldGoToTheCorner()
                && context.getLastDiscovery().getEchoResponse().getFound().isEquals(Found.GROUND))
            mediator.setGoToTheCorner(true);

        int a = 0;

        if (mediator.shouldGoToTheCorner() && context.getLastDiscovery().getEchoResponse().getRange() <= mediator.getRangeToTheCorner()) {
            mediator.setRangeToTheCorner(context.getLastDiscovery().getEchoResponse().getRange());
            mediator.setDirectionToTheCorner(context.getLastDiscovery().getEchoResponse().getDirection());
        } 
    }

    /**
     * Set the first position in the map
     * @param context data context of the simulation
     * @param map
     * @throws PositionOutOfMapRange
     */
    public void setFirtsPosition(Context context, Map map) throws PositionOutOfMapRange {
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