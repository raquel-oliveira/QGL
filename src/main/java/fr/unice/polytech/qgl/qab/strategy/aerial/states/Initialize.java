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
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        if (actionCombo == null) {
            actionCombo = new ComboEchos();
            ((ComboEchos)actionCombo).defineComboEchos(context.getHeading());
        }

        Action act = actionCombo.get(0);
        lastAction = act;
        actionCombo.remove(0);

        if (context.getLastDiscovery() != null && !stateMediator.shouldGoToTheCorner()) {
            if (context.getLastDiscovery().getEchoResponse().getFound().isEquals(Found.GROUND))
                stateMediator.setGoToTheCorner(true);
        }

        if (stateMediator.shouldGoToTheCorner()) {
            if (context.getLastDiscovery().getEchoResponse().getRange() > stateMediator.getRangeToTheCorner()) {
                stateMediator.setRangeToTheCorner(context.getLastDiscovery().getEchoResponse().getRange());
                stateMediator.setDirectionToTheCorner(context.getLastDiscovery().getEchoResponse().getDirection());
            }
        }

        return act;
    }

    private void setFirtsPosition(Context context, Map map) throws PositionOutOfMapRange {
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