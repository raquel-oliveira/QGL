package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
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
public class GoToTheCornerTest {
    GoToTheCorner goToTheCorner;
    Context context;

    @Before
    public void defineContext() throws NegativeBudgetException {
        context = new Context();
        context.setFirstHead(Direction.SOUTH);
        context.setHeading(Direction.SOUTH);

        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.GROUND, Direction.SOUTH, 40);
        Discovery dis = new Discovery();
        dis.setEchoResponse(echoResponse);
        context.setLastDiscovery(dis);

        goToTheCorner = new GoToTheCorner();
    }

    @Test
    public void testInstance() {
        GoToTheCorner go = new GoToTheCorner();
        assertEquals(goToTheCorner.getClass(), go.getClass());
    }

    @Test
    public void testGetState() throws NegativeBudgetException {
        AerialState state = goToTheCorner.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(state.getClass(), goToTheCorner.getClass());
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        StateMediator state = StateMediator.getInstance();
        state.setDirectionToTheCorner(Direction.WEST);
        state.setRangeToTheCorner(15);

        Action act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), Heading.class);

        // if the echo return out_of_range but in the direction that it's not the first head
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.WEST, 40);
        Discovery dis = new Discovery();
        dis.setEchoResponse(echoResponse);
        context.setLastDiscovery(dis);

        act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), Fly.class);

        act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), Echo.class);

        // if the echo return ground in the diretion of the first head
        echoResponse = new EchoResponse();
        echoResponse.addData(Found.GROUND, Direction.SOUTH, 40);
        dis = new Discovery();
        dis.setEchoResponse(echoResponse);
        context.setLastDiscovery(dis);

        act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), Fly.class);

        act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), Echo.class);
    }

    @Test
    public void foundOutOfRange() throws IndexOutOfBoundsComboAction {
        StateMediator state = StateMediator.getInstance();
        state.setDirectionToTheCorner(Direction.WEST);
        state.setRangeToTheCorner(15);

        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.SOUTH, 40);
        Discovery dis = new Discovery();
        dis.setEchoResponse(echoResponse);
        context.setLastDiscovery(dis);

        Action act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(act.getClass(), Heading.class);

        AerialState aerialState = goToTheCorner.getState(context, new Map(), state);
        assertEquals(Initialize.class, aerialState.getClass());
    }

    @Test
    public void testWithoutSpace() throws IndexOutOfBoundsComboAction {
        StateMediator state = StateMediator.getInstance();
        state.setDirectionToTheCorner(Direction.WEST);
        state.setRangeToTheCorner(1);

        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.GROUND, Direction.SOUTH, 40);
        Discovery dis = new Discovery();
        dis.setEchoResponse(echoResponse);
        context.setLastDiscovery(dis);

        Action act = goToTheCorner.responseState(context, new Map(), state);
        assertEquals(Heading.class, act.getClass());
    }
}
