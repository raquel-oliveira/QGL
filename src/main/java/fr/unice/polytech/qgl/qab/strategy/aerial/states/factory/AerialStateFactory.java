package fr.unice.polytech.qgl.qab.strategy.aerial.states.factory;

import fr.unice.polytech.qgl.qab.strategy.aerial.states.*;

/**
 * Class factory to return a new instance of each state in the aerial strategy.
 * @version 13/03/16.
 */
public class AerialStateFactory {

    private AerialStateFactory() {
    }

    /**
     * Method that returns a new instance of each state.
     * @param stateType return a new instance of the stateType
     * @return new instance
     */
    public static AerialState buildState(AerialStateType stateType) {
        AerialState state;

        switch (stateType) {
            case INITIALIZE:
                state = new Initialize();
                break;

            case GO_TO_THE_CORNER:
                state = new GoToTheCorner();
                break;

            case FIND_GROUND:
                state = new FindGround();
                break;

            case FLY_UNTIL:
                state = new FlyUntil();
                break;

            case SCAN_THE_GROUND:
                state = new ScanTheGround();
                break;

            case RETURN_BACK:
                state = new ReturnBack();
                break;

            case LAND_IN_GROUND:
                state = new LandInGround();
                break;

            default:
                state = new StopSimulation();
                break;
        }
        return state;
    }
}
