package fr.unice.polytech.qgl.qab.strategy.ground.factory;

import fr.unice.polytech.qgl.qab.strategy.ground.states.*;
import fr.unice.polytech.qgl.qab.strategy.ground.states.ScoutTile;

/**
 * @version 13/03/16.
 */
public class GroundStateFactory {

    private GroundStateFactory() {
    }

    public static GroundState buildState(GroundStateType model) {
        GroundState state;

        switch (model) {
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
