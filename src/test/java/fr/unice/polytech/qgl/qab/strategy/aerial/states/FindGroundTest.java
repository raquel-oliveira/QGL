package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 31/12/15.
 */
public class FindGroundTest {
    FindGround findGround;
    Context context;

    @Before
    public void defineContext() throws NegativeBudgetException {
        findGround = FindGround.getInstance();
        context = new Context();
        context.setLastDiscovery(new Discovery());
    }

    @Test
    public void testInstance() {
        FindGround find = FindGround.getInstance();
        assertEquals(findGround, find);
    }

    @Test
    public void testGetState() throws NegativeBudgetException {
        AerialState state = findGround.getState(new Context(), new Map(), StateMediator.getInstance());
        assertEquals(state, findGround);
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        context.setHeading(Direction.NORTH);
        context.setFirstHead(Direction.NORTH);
        Action act = findGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), new Fly().getClass());

        context.setLastDiscovery(new Discovery());
        context.setHeading(Direction.NORTH);
        context.setFirstHead(Direction.NORTH);
        //context.setLastDiscovery(new Discovery(Found.GROUND, 10, Direction.EAST));

        act = findGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), new Echo(Direction.EAST).getClass());

        act = findGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), new Heading(Direction.EAST).getClass());

        AerialState aerialState = findGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(aerialState.getClass(), FlyUntil.getInstance().getClass());

        act = findGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), new Echo(Direction.WEST).getClass());
    }
}
