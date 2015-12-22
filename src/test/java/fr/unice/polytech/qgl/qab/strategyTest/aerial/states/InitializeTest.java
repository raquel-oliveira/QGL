package fr.unice.polytech.qgl.qab.strategyTest.aerial.states;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.AerialState;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.Initialize;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.StateMediator;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
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

    @Before
    public void defineContext() throws NegativeBudgetException {
        initialize = Initialize.getInstance();
        Discovery discovery = new Discovery();
        context = new Context();
        context.setLastDiscovery(discovery);
    }

    @Ignore
    public void testInitializeDimention() { // TODO: finalizar
        Map map = new Map();
        StateMediator stateMediator = StateMediator.getInstance();
        stateMediator.setGoToTheCorner(false);

        Discovery discovery = new Discovery();
        discovery.setRange(10);
        discovery.setFound(Found.OUT_OF_RANGE);

        initialize.responseState(context, map, StateMediator.getInstance());
        AerialState state = initialize.getState(context, new Map(), StateMediator.getInstance());

        assertEquals(Initialize.getInstance(), state);
    }
}
