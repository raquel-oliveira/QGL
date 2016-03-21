package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 11.12.2015.
 */
public abstract class AerialState {
    /**
     * Get the state current
     * @param context
     * @param map
     * @param stateMediator
     * @return return the state
     * @throws PositionOutOfMapRange
     */
    public abstract AerialState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapRange;

    /**
     * Determine the action chosed by the state
     * @param context
     * @param map
     * @param stateMediator
     * @return return the action
     * @throws IndexOutOfBoundsComboAction
     */
    public abstract Action responseState(Context context,  Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction;
}