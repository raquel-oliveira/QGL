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
            case FINDTILE:
                state = new FindTile();
                break;

            case EXPLOITTILE:
                state = new ExploitTile();
                break;

            case EXPLORELITTLETILE:
                state = new ExploreLittleTile();
                break;

            case EXPLORETILE:
                state = new ScoutTile();
                break;

            case SCOUTTILE:
                state = new ScoutTile();
                break;

            default:
                state = new StopSimulation();
                break;
        }
        return state;
    }
}
