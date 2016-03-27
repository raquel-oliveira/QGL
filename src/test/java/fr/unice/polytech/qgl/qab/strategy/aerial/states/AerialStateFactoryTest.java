package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateFactory;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version 13/03/16.
 */
public class AerialStateFactoryTest {

    @Test
    public void testInitialization() {
        AerialState state;
        state = AerialStateFactory.buildState(AerialStateType.INITIALIZE);
        assertEquals(Initialize.class, state.getClass());

        state = AerialStateFactory.buildState(AerialStateType.SCAN_THE_GROUND);
        assertEquals(ScanTheGround.class, state.getClass());

        state = AerialStateFactory.buildState(AerialStateType.FLY_UNTIL);
        assertEquals(FlyUntil.class, state.getClass());

        state = AerialStateFactory.buildState(AerialStateType.RETURN_BACK);
        assertEquals(ReturnBack.class, state.getClass());

        state = AerialStateFactory.buildState(AerialStateType.FIND_GROUND);
        assertEquals(FindGround.class, state.getClass());

        state = AerialStateFactory.buildState(AerialStateType.GO_TO_THE_CORNER);
        assertEquals(GoToTheCorner.class, state.getClass());

        state = AerialStateFactory.buildState(AerialStateType.LAND_IN_GROUND);
        assertEquals(LandInGround.class, state.getClass());
    }

    @Test (expected = NullPointerException.class)
    public void testEnumError() {
        AerialState state = AerialStateFactory.buildState(null);
        assertEquals(Stop.class, state.getClass());
    }
}
