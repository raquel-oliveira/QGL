package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 31/12/15.
 */
public abstract class GroundState {
    Action lastAction;

    public GroundState() {
        lastAction = null;
    }

    public abstract GroundState getState(Context context, Map map) throws PositionOutOfMapRange;

    public abstract Action responseState(Context context,  Map map) throws IndexOutOfBoundsComboAction;
}
