package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 31/01/16.
 */
public class FlyUntilTest {
    FlyUntil flyUntil;

    @Before
    public void defineContext() {
        flyUntil = FlyUntil.getInstance();
    }

    @Test
    public void testInstance() {
        FlyUntil end = FlyUntil.getInstance();
        assertEquals(flyUntil, end);
    }

    @Test
    public void testGetState() throws NegativeBudgetException {
        AerialState state = flyUntil.getState(new Context(), new Map(), StateMediator.getInstance());
        assertEquals(state, ScanTheGround.getInstance());
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        Action act = flyUntil.responseState(new Context(), new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), new Fly().getClass());
    }
}
