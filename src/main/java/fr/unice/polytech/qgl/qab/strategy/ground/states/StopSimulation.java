package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.AerialState;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.StateMediator;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 13/03/16.
 */
public class StopSimulation extends GroundState {
    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        return null;
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        return null;
    }
}
