package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 21/02/16.
 */
public class ExploreTileTest {
    ExploreTile exploreTile;

    @Before
    public void defineContext() {
        exploreTile = ExploreTile.getInstance();
    }

    @Test
    public void testInstance() {
        ExploreTile explore = ExploreTile.getInstance();
        assertEquals(exploreTile.getClass(), explore.getClass());
    }

    @Ignore
    public void testActions() throws NegativeBudgetException, PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        GroundState state = exploreTile.getState(new Context(), new Map());
        assertEquals(state, exploreTile);

        Action act = exploreTile.responseState(new Context(), new Map());
        assertEquals(Explore.class, act.getClass());

        state = exploreTile.getState(new Context(), new Map());
        assertEquals(state, exploreTile);

        act = exploreTile.responseState(new Context(), new Map());
        assertEquals(MoveTo.class, act.getClass());
    }
}
