package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 11.12.2015.
 */
public abstract class State {

    protected static Action lastAction;

    public void State() {
        lastAction = null;
    }

    public abstract State getState(Context context, Map map);

    public abstract Action responseState(Context context,  Map map);
}