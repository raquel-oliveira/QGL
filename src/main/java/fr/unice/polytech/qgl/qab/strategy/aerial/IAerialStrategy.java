package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 *
 * @version 9.12.2015
 */
public interface IAerialStrategy {
    public abstract Action makeDecision(Context context);
}
