package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.response.EchoResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @version 22/12/15.
 */
public class InitializeTest {
    Initialize initialize;
    Context context;
    Discovery discovery;
    StateMediator stateMediator;
    Map map;

    @Before
    public void defineContext() throws NegativeBudgetException {
        initialize = Initialize.getInstance(context);
        discovery = new Discovery();
        context = new Context();
        stateMediator = StateMediator.getInstance();
        map = new Map();

        context.setHeading(Direction.SOUTH);
        context.setFirstHead(Direction.SOUTH);
    }

    @Ignore
    public void testInitializeWithoutFoundAGround() throws NegativeBudgetException, IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        AerialState state = initialize.getState(context, map, stateMediator);
        assertEquals(Initialize.class, state.getClass());

        Action act = initialize.responseState(context, map, stateMediator);
        assertEquals(Echo.class, act.getClass());

        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.SOUTH, 10);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);
        context.setFirstHead(Direction.SOUTH);

        state = initialize.getState(context, map, stateMediator);
        assertEquals(Initialize.class, state.getClass());

        act = initialize.responseState(context, map, stateMediator);
        assertEquals(Echo.class, act.getClass());

        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.WEST, 10);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);
        context.setFirstHead(Direction.SOUTH);

        state = initialize.getState(context, map, stateMediator);
        assertEquals(Initialize.class, state.getClass());

        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.EAST, 10);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);
        context.setFirstHead(Direction.SOUTH);

        act = initialize.responseState(context, map, stateMediator);
        assertEquals(Echo.class, act.getClass());

        state = initialize.getState(context, map, stateMediator);
        assertEquals(FindGround.class, state.getClass());
    }

    @Ignore
    public void testInitializeAfterFoundAGround() throws IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        // first moment = just make a echo
        Action act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

        // found a out_of_range as response
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.EAST, 10);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

        stateMediator.setGoToTheCorner(false);
        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.WEST, 10);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        AerialState state = initialize.getState(context, map, StateMediator.getInstance());
        assertEquals(Initialize.getInstance(context), state);
        act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

        state = initialize.getState(context, map, StateMediator.getInstance());
        assertEquals(Initialize.getInstance(context), state);

        act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.SOUTH, 4);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        state = initialize.getState(context, map, StateMediator.getInstance());
        assertEquals(Initialize.getInstance(context), state);

        act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

        assertEquals(2, map.getHeight());
        assertEquals(2, map.getWidth());
    }
}
