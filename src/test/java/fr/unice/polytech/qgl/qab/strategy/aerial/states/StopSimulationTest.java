package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.response.EchoResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by huang on 13/03/16.
 */
public class StopSimulationTest {
    StopSimulation stopSimulation;
    Context context;

    @Before
    public void defineSimulation() throws NegativeBudgetException {
        context = new Context();
        context.setFirstHead(Direction.NORTH);
        context.setHeading(Direction.NORTH);

        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.GROUND, Direction.SOUTH, 40);
        Discovery dis = new Discovery();
        dis.setEchoResponse(echoResponse);
        context.setLastDiscovery(dis);

        stopSimulation = new StopSimulation();
    }

    @Test
    public void testGetState() throws PositionOutOfMapRange {
        AerialState state = stopSimulation.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(null, state);
    }

    @Test
    public void testResponseState() throws IndexOutOfBoundsComboAction {
        Action action = stopSimulation.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(action.getClass(), Stop.class);
    }
}
