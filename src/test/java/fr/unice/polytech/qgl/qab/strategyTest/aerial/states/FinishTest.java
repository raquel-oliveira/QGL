package fr.unice.polytech.qgl.qab.strategyTest.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.AerialState;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.Finish;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.StateMediator;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @version 22/12/15.
 */
public class FinishTest {
    Finish finish;

    @Before
    public void defineContext() {
        finish = Finish.getInstance();
    }

    @Test
    public void testInstance() {
        Finish end = Finish.getInstance();
        assertEquals(finish, end);
    }

    @Test
    public void testGetState() throws NegativeBudgetException {
        AerialState state = finish.getState(new Context(), new Map(), StateMediator.getInstance());
        assertEquals(state, finish);
    }

    @Test
    public void testResponseState() throws NegativeBudgetException {
        Action act = finish.responseState(new Context(), new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), new Stop().getClass());
    }
}
