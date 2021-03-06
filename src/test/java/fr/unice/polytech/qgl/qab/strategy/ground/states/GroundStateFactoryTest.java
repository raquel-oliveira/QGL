package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateFactory;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 13/03/16.
 */
public class GroundStateFactoryTest {

    @Test
    public void testInitialization() {
        GroundState state;
        state = GroundStateFactory.buildState(GroundStateType.FIND_TILE);
        assertEquals(FindTile.class, state.getClass());

        state = GroundStateFactory.buildState(GroundStateType.SCOUT_TILE);
        assertEquals(ScoutTile.class, state.getClass());

        state = GroundStateFactory.buildState(GroundStateType.EXPLOIT_TILE);
        assertEquals(ExploitTile.class, state.getClass());
    }

    @Test (expected = NullPointerException.class)
    public void testEnumError() {
        GroundState state = GroundStateFactory.buildState(null);
        assertEquals(Stop.class, state.getClass());
    }
}
