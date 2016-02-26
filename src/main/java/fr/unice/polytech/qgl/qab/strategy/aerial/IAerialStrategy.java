package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 *
 * @version 9.12.2015
 */
public interface IAerialStrategy {

    /**
     * Method to determine which action is played in each step
     * @param context
     * @return return the action used in next step
     * @throws PositionOutOfMapRange
     * @throws IndexOutOfBoundsComboAction
     */
    Action makeDecision(Context context, Map map) throws PositionOutOfMapRange, IndexOutOfBoundsComboAction;
}
