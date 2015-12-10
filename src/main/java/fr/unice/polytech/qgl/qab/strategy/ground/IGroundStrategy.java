package fr.unice.polytech.qgl.qab.strategy.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * Created by gabriela on 09/12/15.
 */
public interface IGroundStrategy {
    public abstract Action makeDecision(Context context);
}
