package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 11.12.2015.
 */
public abstract class AerialState {
    Action lastAction;

    AerialState() {
        lastAction = null;
    }

    public abstract AerialState getState(Context context, Map map, StateMediator stateMediator);

    public abstract Action responseState(Context context,  Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction;
}