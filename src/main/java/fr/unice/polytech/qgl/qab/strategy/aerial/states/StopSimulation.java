package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 23/02/16.
 */
public class StopSimulation extends AerialState {

    public static StopSimulation getInstance() {
        return new StopSimulation();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapRange {
        return new StopSimulation();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        return new Stop();
    }
}
