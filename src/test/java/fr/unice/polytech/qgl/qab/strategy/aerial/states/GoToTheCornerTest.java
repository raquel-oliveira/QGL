package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 31/12/15.
 */
public class GoToTheCornerTest {
    GoToTheCorner goToTheCorner;

    @Before
    public void defineContext() {
        goToTheCorner = GoToTheCorner.getInstance();
    }

    @Test
    public void testInstance() {
        GoToTheCorner go = GoToTheCorner.getInstance().getInstance();
        assertEquals(goToTheCorner, go);
    }

    @Test
    public void testGetState() throws NegativeBudgetException {
        AerialState state = goToTheCorner.getState(new Context(), new Map(), StateMediator.getInstance());
        assertEquals(state, goToTheCorner);
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        StateMediator state = StateMediator.getInstance();
        state.setDirectionToTheCorner(Direction.EAST);
        state.setRangeToTheCorner(5);

        Context context = new Context();
        context.setFirstHead(Direction.NORTH);
        context.setHeading(Direction.NORTH);

        Action act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Heading(Direction.EAST).getClass());

        act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Fly().getClass());

        act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Fly().getClass());

        act = goToTheCorner.responseState(new Context(), new Map(), state);
        assertEquals(act.getClass(), new Fly().getClass());

        act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Heading(Direction.NORTH).getClass());

        AerialState aerialState = goToTheCorner.getState(context, new Map(), state);
        assertEquals(Initialize.getInstance(), aerialState);
    }
}
