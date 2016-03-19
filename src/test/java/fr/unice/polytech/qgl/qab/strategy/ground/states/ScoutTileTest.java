package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 07/03/16.
 */
public class ScoutTileTest {
    ExploreTile scoutTile;

    @Before
    public void defineContext() {
        scoutTile = new ExploreTile();
    }

    @Test
    public void testState() throws NegativeBudgetException, IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        Context context = new Context();

        testAction(Direction.WEST, 1, context);

        testAction(Direction.NORTH, 1, context);

        testAction(Direction.EAST, 2, context);

        testAction(Direction.SOUTH, 2, context);

        testAction(Direction.WEST, 2, context);

        Action action = scoutTile.responseState(context, new Map());
        assertEquals(MoveTo.class, action.getClass());
        assertEquals(Direction.NORTH, action.getDirection());
        action = scoutTile.responseState(context, new Map());
        assertEquals(MoveTo.class, action.getClass());
        assertEquals(Direction.EAST, action.getDirection());

        GroundState state = scoutTile.getState(context, new Map());
        assertEquals(FindTile.class, state.getClass());

    }

    private void testAction(Direction dir, int interations, Context context) throws IndexOutOfBoundsComboAction {
        Action action;
        for (int i = 0; i < interations; i++) {
            action = scoutTile.responseState(context, new Map());
            assertEquals(Explore.class, action.getClass());
            action = scoutTile.responseState(context, new Map());
            assertEquals(MoveTo.class, action.getClass());
            assertEquals(dir, action.getDirection());
        }
    }
}
