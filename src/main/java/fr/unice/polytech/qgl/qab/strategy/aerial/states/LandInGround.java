package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Land;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateFactory;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 23/02/16.
 */
public class LandInGround extends AerialState {

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapRange {
        return AerialStateFactory.buildState(AerialStateType.LANDINGROUND);
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        // if any creek was found
        map.getBestCreek(context);
        Position p = map.getCreekLand();
        Action act;
        if (map.getTile(p) != null) {
            act = new Land(map.getTile(p).getCreek().get(0).getIdCreek(), 1);
        } else {
            act = new Land(context.getLastDiscovery().getCreeks().get(0).getIdCreek(), 1);
        }
        context.current().setLastAction(act);
        return act;
    }
}
