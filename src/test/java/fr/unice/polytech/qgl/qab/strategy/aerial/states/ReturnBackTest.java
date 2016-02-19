package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
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
 * @version 30/01/16.
 */
public class ReturnBackTest {
    ReturnBack returnBack;

    @Before
    public void defineContext() {
        returnBack = ReturnBack.getInstance();
    }

    @Test
    public void testInstance() {
        ReturnBack back = ReturnBack.getInstance();
        assertEquals(returnBack, back);
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        /*Context context = new Context();
        context.setFirstHead(Direction.NORTH);
        context.setHeading(Direction.EAST);

        StateMediator state = StateMediator.getInstance();

        //assertEquals(act.getClass(), new Heading(Direction.NORTH).getClass());
        AerialState as = returnBack.getState(context, new Map(), state);
        assertEquals(as.getClass(), ReturnBack.getInstance().getClass());

        Action act = returnBack.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Heading(Direction.NORTH).getClass());

        as = returnBack.getState(context, new Map(), state);
        assertEquals(as.getClass(), ReturnBack.getInstance().getClass());

        act = returnBack.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Heading(Direction.WEST).getClass());

        as = returnBack.getState(context, new Map(), state);
        assertEquals(as.getClass(), ReturnBack.getInstance().getClass());

        act = returnBack.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Echo(Direction.WEST).getClass());

        Discovery discovery = new Discovery();
        discovery.setRange(0);
        context.setLastDiscovery(discovery);

        as = returnBack.getState(context, new Map(), state);
        assertEquals(as.getClass(), ScanTheGround.getInstance().getClass());

        discovery = new Discovery();
        discovery.setFound(Found.OUT_OF_RANGE);
        context.setLastDiscovery(discovery);

        act = returnBack.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Stop().getClass());

        discovery = new Discovery();
        discovery.setFound(Found.GROUND);
        discovery.setRange(2);
        context.setLastDiscovery(discovery);

        act = returnBack.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Fly().getClass());

        act = returnBack.responseState(context, new Map(), state);
        assertEquals(act.getClass(), new Fly().getClass());

        assertEquals(false, state.shouldFlyUntilGround());*/
    }
}
