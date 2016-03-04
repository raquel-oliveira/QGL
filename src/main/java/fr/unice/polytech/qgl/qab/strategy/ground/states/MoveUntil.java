package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 01/03/16.
 */
public class MoveUntil extends GroundState {

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        return null;
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        if (context.current().getComboAction() == null) {

        }
        return null;
    }
}
