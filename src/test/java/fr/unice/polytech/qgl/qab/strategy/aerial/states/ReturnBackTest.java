package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
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
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 30/01/16.
 */
public class ReturnBackTest {
    ReturnBack returnBack;

    @Before
    public void defineContext() {
        returnBack = new ReturnBack();
    }

    @Test
    public void testInstance() {
        ReturnBack back = new ReturnBack();
        assertEquals(returnBack.getClass(), back.getClass());
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        Context context = new Context();
        context.setFirstHead(Direction.NORTH);
        context.setHeading(Direction.EAST);

        StateMediator state = StateMediator.getInstance();

        Map map = new Map();
        map.setLastPosition(new Position(0, 0));

        AerialState as = returnBack.getState(context, map, state);
        assertEquals(as.getClass(), ReturnBack.class);

        testHeading(context, state, map);

        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.GROUND, Direction.WEST, 0);
        Discovery discovery = new Discovery();
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        as = returnBack.getState(context, map, state);
        assertEquals(as.getClass(), ScanTheGround.class);
    }

    @Test
    public void testFlyUntilGround() throws IndexOutOfBoundsComboAction, NegativeBudgetException {
        Context context = new Context();
        context.setFirstHead(Direction.NORTH);
        context.setHeading(Direction.EAST);

        StateMediator state = StateMediator.getInstance();

        Map map = new Map();
        map.setLastPosition(new Position(0, 0));

        AerialState as = returnBack.getState(context, map, state);
        assertEquals(as.getClass(), ReturnBack.class);

        testHeading(context, state, map);

        Discovery discovery = new Discovery();
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.GROUND, Direction.WEST, 2);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        AerialState act = returnBack.getState(context, map, state);
        assertEquals(FlyUntil.class, act.getClass());
    }

    private void testHeading(Context context, StateMediator state, Map map) throws IndexOutOfBoundsComboAction {
        AerialState as;
        Action act = returnBack.responseState(context, map, state);
        assertEquals(act.getClass(), Heading.class);

        as = returnBack.getState(context, map, state);
        assertEquals(as.getClass(), ReturnBack.class);

        act = returnBack.responseState(context, map, state);
        assertEquals(act.getClass(), Heading.class);

        as = returnBack.getState(context, map, state);
        assertEquals(as.getClass(), ReturnBack.class);

        act = returnBack.responseState(context, map, state);
        assertEquals(act.getClass(), Echo.class);
    }
}
