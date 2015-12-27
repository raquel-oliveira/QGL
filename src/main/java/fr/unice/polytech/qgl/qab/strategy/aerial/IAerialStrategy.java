package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 *
 * @version 9.12.2015
 */
public interface IAerialStrategy {
    Action makeDecision(Context context) throws PositionOutOfMapRange, IndexOutOfBoundsComboAction;
}
