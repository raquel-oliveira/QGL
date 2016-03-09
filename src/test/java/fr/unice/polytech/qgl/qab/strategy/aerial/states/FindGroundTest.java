package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.response.EchoResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Before;
import org.junit.Ignore;
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
        findGround = new FindGround();
        context = new Context();
        context.setLastDiscovery(new Discovery());
    }

    @Test
    public void testInstance() {
        FindGround find = new FindGround();
        assertEquals(FindGround.class, find.getClass());
    }

    @Test
    public void testGetState() throws NegativeBudgetException {
        AerialState state = findGround.getState(new Context(), new Map(), StateMediator.getInstance());
        assertEquals(FindGround.class, state.getClass());
    }

    @Test
    public void testChoiceASideVertical() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        context.setHeading(Direction.NORTH);
        context.setFirstHead(Direction.NORTH);

        Map map = new Map();
        map.setLastPosition(new Position(80, 80));
        map.initializeWidthMap(100, true);
        map.initializeHeightMap(100, true);

        Action act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Fly.class);

        AerialState aerialState = findGround.getState(context, map, StateMediator.getInstance());
        assertEquals(aerialState.getClass(), FindGround.class);

        act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Echo.class);
        assertEquals(Direction.WEST, act.getDirection());
    }

    @Test
    public void testChoiceASideHorizontalSouth() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        Map map = setContextMap(Direction.EAST, new Position(80, 80));

        Action act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Fly.class);

        AerialState aerialState = findGround.getState(context, map, StateMediator.getInstance());
        assertEquals(aerialState.getClass(), FindGround.class);

        act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Echo.class);
        assertEquals(Direction.NORTH, act.getDirection());
    }

    @Test
    public void testChoiceASideHorizontalNorth() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        Map map = setContextMap(Direction.EAST, new Position(30, 30));

        Action act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Fly.class);

        AerialState aerialState = findGround.getState(context, map, StateMediator.getInstance());
        assertEquals(aerialState.getClass(), FindGround.class);

        act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Echo.class);
        assertEquals(Direction.SOUTH, act.getDirection());
    }

    private Map setContextMap(Direction east, Position position) {
        context.setHeading(east);
        context.setFirstHead(east);

        Map map = new Map();
        map.setLastPosition(position);
        map.initializeWidthMap(100, true);
        map.initializeHeightMap(100, true);
        return map;
    }

    @Test
    public void testResponseState() throws IndexOutOfBoundsComboAction {
        context.setHeading(Direction.NORTH);
        context.setFirstHead(Direction.NORTH);

        Map map = new Map();
        map.setLastPosition(new Position(30, 30));
        map.initializeWidthMap(100, true);
        map.initializeHeightMap(100, true);

        Action act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Fly.class);

        AerialState aerialState = findGround.getState(context, map, StateMediator.getInstance());
        assertEquals(aerialState.getClass(), FindGround.class);

        act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Echo.class);
        assertEquals(Direction.EAST, act.getDirection());

        aerialState = findGround.getState(context, map, StateMediator.getInstance());
        assertEquals(aerialState.getClass(), FindGround.class);

        act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Fly.class);

        aerialState = findGround.getState(context, map, StateMediator.getInstance());
        assertEquals(aerialState.getClass(), FindGround.class);

        act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Echo.class);
        assertEquals(Direction.EAST, act.getDirection());

        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.GROUND, Direction.EAST, 10);
        Discovery disc = new Discovery();
        disc.setEchoResponse(echoResponse);
        context.setLastDiscovery(disc);

        act = findGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Heading.class);
        assertEquals(Direction.EAST, act.getDirection());

        aerialState = findGround.getState(context, map, StateMediator.getInstance());
        assertEquals(aerialState.getClass(), FlyUntil.class);
    }
}
