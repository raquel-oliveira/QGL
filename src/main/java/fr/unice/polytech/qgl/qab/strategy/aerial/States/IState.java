package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;

/**
 * @version 11.12.2015.
 */
public interface IState {
    public ResponseState responseState(Context context);
}
