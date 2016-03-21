package fr.unice.polytech.qgl.qab.strategy.ground.factory;

import fr.unice.polytech.qgl.qab.strategy.ground.states.*;

/**
 * @version 13/03/16.
 */
public class GroundStateFactory {

    private GroundStateFactory() {
    }

    public static GroundState buildState(GroundStateType model) {
        GroundState state;

        switch (model) {
            case FINDTILE:
                state = new FindTile();
                break;

            case EXPLOITTILE:
                state = new ExploitTile();
                break;

            case EXPLORETILE:
                state = new ExploreTile();
                break;

            case SCOUTTILE:
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
