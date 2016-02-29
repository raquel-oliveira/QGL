package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Land;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @version 22/12/15.
 */
public class FinishTest {
    Finish finish;

    @Before
    public void defineContext() {
        finish = Finish.getInstance();
    }

    @Test
    public void testInstance() {
        Finish end = Finish.getInstance();
        assertEquals(finish, end);
    }

    @Test
    public void testGetState() throws NegativeBudgetException {
        AerialState state = finish.getState(new Context(), new Map(), StateMediator.getInstance());
        assertEquals(state, finish);
    }

    @Test
    public void testResponseState() throws NegativeBudgetException {
        Discovery discovery = new Discovery();
        Context context = new Context();
        List<Creek> creeks = new ArrayList<>();
        creeks.add(new Creek("adaet-124fqdfaae-avaer"));
        discovery.setCreeks(creeks);
        context.setLastDiscovery(discovery);

        Action act = finish.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), Land.class);
    }
}
