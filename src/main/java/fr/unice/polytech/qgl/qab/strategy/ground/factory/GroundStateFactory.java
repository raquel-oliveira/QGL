package fr.unice.polytech.qgl.qab.strategy.ground.factory;

import fr.unice.polytech.qgl.qab.strategy.ground.states.*;

/**
 * @version 13/03/16.
 */
public class GroundStateFactory {
    public static GroundState buildState(GroundStateType model) {
        GroundState state = null;

        switch (model) {
            case FINDTILE:
                state = new FindTile();
                break;

            case EXPLOIT:
                state = new ExploitTile();
                break;

            case EXPLORETILE:
                state = new ExploreTile();
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
