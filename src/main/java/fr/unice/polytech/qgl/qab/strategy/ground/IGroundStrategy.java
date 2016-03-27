package fr.unice.polytech.qgl.qab.strategy.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.AccessException;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 09/12/15.
 */
public interface IGroundStrategy {
    /**
     * Method responsable by make the decision about what is the best action to the ground phase
     * @param context values about the context of this simulation
     * @return the best action
     * @throws PositionOutOfMapRange if a irregular position tried to be accessed in the map
     * @throws IndexOutOfBoundsComboAction if a irregular index tried to be acessed in the combo of action
     */
    Action makeDecision(Context context, Map map) throws AccessException;
}
