package fr.unice.polytech.qgl.qab.strategy.aerial.states.factory;

import fr.unice.polytech.qgl.qab.strategy.aerial.states.*;

/**
 * @version 13/03/16.
 */
public class AerialStateFactory {
    public static AerialState buildState(AerialStateType model) {
        AerialState state = null;

        switch (model) {
            case INITIALIZE:
                state = new Initialize();
                break;

            case GOTOTHECORNER:
                state = new GoToTheCorner();
                break;

            case FINDGROUND:
                state = new FindGround();
                break;

            case FLYUNTIL:
                state = new FlyUntil();
                break;

            case SCANTHEGROUND:
                state = new ScanTheGround();
                break;

            case RETURNBACK:
                state = new ReturnBack();
                break;

            case LANDINGROUND:
                state = new LandInGround();
                break;

            default:
                state = new StopSimulation();
                break;
        }
        return state;
    }
}
