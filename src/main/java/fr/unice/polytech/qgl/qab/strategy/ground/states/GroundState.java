package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 31/12/15.
 *
 * Abstract class tha represents the ground state.
 */
public abstract class GroundState {
    /**
     * GroundState's contructor
     */
    public GroundState() {

    }

    /**
     * Method the return the current state
     * @param context datas about the context of the simulation
     * @param map of the simulation
     * @return  the current state
     * @throws PositionOutOfMapRange
     */
    public abstract GroundState getState(Context context, Map map) throws PositionOutOfMapRange;

    /**
     * Method that return the response (action) chose by the state
     * @param context datas about the context of the simulation
     * @param map of the simulation
     * @return the action chose by the state
     * @throws IndexOutOfBoundsComboAction
     */
    public abstract Action responseState(Context context,  Map map) throws IndexOutOfBoundsComboAction;
}
