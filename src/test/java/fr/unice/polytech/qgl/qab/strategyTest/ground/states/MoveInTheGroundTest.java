package fr.unice.polytech.qgl.qab.strategyTest.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.actions.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.ground.states.GroundState;
import fr.unice.polytech.qgl.qab.strategy.ground.states.MoveInTheGround;
import fr.unice.polytech.qgl.qab.strategy.ground.states.StateManager;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 30/01/16.
 */
public class MoveInTheGroundTest {
    MoveInTheGround moveInTheGround;

    @Before
    public void defineContext() {
        moveInTheGround = MoveInTheGround.getInstance();
    }

    @Test
    public void testInstance() {
        MoveInTheGround move = MoveInTheGround.getInstance();
        assertEquals(moveInTheGround, move);
    }

    @Test
    public void testGetState() throws NegativeBudgetException, PositionOutOfMapRange {
        GroundState state = moveInTheGround.getState(new Context(), new Map(), StateManager.getInstance());
        assertEquals(state, moveInTheGround);
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        Action act = moveInTheGround.responseState(new Context(), new Map(), StateManager.getInstance());
        assertEquals(act.getClass(), new MoveTo(Direction.EAST).getClass());
    }
}