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
        initialize = Initialize.getInstance();
        discovery = new Discovery();
        context = new Context();
        stateMediator = StateMediator.getInstance();
        map = new Map();

        context.setHeading(Direction.SOUTH);
        context.setFirstHead(Direction.SOUTH);
    }

    @Test
    public void testFirtsEcho() throws NegativeBudgetException, IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.SOUTH, 1);
        discovery.setEchoResponse(echoResponse);
        discovery.setDirection(Direction.NORTH);
        context.setLastDiscovery(discovery);
        context.setFirstHead(Direction.NORTH);

        AerialState state = initialize.getState(context, map, stateMediator);
        assertEquals(Initialize.class, state.getClass());

        Action act = initialize.responseState(context, map, stateMediator);
        assertEquals(Echo.class, act.getClass());
    }

    @Test
    public void testInitializeDimention() throws IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.SOUTH, 1);
        discovery.setEchoResponse(echoResponse);
        discovery.setDirection(Direction.NORTH);
        context.setLastDiscovery(discovery);
        context.setFirstHead(Direction.NORTH);

        AerialState state = initialize.getState(context, map, StateMediator.getInstance());
        assertEquals(Initialize.getInstance(), state);

        Action act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

        //state = initialize.getState(context, map, StateMediator.getInstance());
        assertEquals(Initialize.getInstance(), state);

        //act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

        stateMediator.setGoToTheCorner(false);
        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.SOUTH, 1);
        discovery.setEchoResponse(echoResponse);
        discovery.setDirection(act.getDirection());
        context.setLastDiscovery(discovery);

//        state = initialize.getState(context, map, StateMediator.getInstance());
        assertEquals(Initialize.getInstance(), state);

        //act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.SOUTH, 4);
        discovery.setEchoResponse(echoResponse);
        discovery.setDirection(act.getDirection());
        context.setLastDiscovery(discovery);

//        state = initialize.getState(context, map, StateMediator.getInstance());
//        assertEquals(Initialize.getInstance(), state);

//        act = initialize.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());

       // assertEquals(2, map.getHeight());
//        assertEquals(2, map.getWidth());
    }
}
