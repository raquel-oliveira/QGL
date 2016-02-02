package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 30/01/16.
 */
public class ExitTest {
    Exit e;

    @Before
    public void defineContext() {
        e = Exit.getInstance();
    }

    @Test
    public void testInstance() {
        Exit exit = Exit.getInstance();
        assertEquals(e, exit);
    }

    @Test
    public void testGetState() throws NegativeBudgetException, PositionOutOfMapRange {
        GroundState state = e.getState(new Context(), new Map(), StateManager.getInstance());
        assertEquals(state, e);
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        Action act = e.responseState(new Context(), new Map(), StateManager.getInstance());
        assertEquals(act.getClass(), new Stop().getClass());
    }
}
