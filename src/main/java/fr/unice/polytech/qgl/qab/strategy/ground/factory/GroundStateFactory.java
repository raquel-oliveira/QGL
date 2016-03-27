package fr.unice.polytech.qgl.qab.strategy.ground.factory;

import fr.unice.polytech.qgl.qab.strategy.ground.states.*;
import fr.unice.polytech.qgl.qab.strategy.ground.states.ScoutTile;

/**
 * Class factory to return a new instance of each state in the ground strategy.
 * @version 13/03/16.
 */
public class GroundStateFactory {

    private GroundStateFactory() {
    }

    /**
     * Method that returns a new instance of each state.
     * @param stateType return a new instance of the stateType
     * @return new instance
     */
    public static GroundState buildState(GroundStateType stateType) {
        GroundState state;

        switch (stateType) {
            case FIND_TILE:
                state = new FindTile();
                break;

            case EXPLOIT_TILE:
                state = new ExploitTile();
                break;

            case SCOUT_TILE:
                state = new ScoutTile();
                break;

            case TRANSFORM:
                state = new TransformResource();
                break;

            default:
                state = new StopSimulation();
                break;
        }
        return state;
    }
}
