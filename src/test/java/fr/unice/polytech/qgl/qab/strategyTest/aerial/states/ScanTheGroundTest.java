package fr.unice.polytech.qgl.qab.strategyTest.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.common.Land;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biome;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.*;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @version 22/12/15.
 */
public class ScanTheGroundTest {
    ScanTheGround scanTheGround;
    Context context;

    @Before
    public void defineContext() throws NegativeBudgetException {
        scanTheGround = ScanTheGround.getInstance();
        Discovery discovery = new Discovery();
        discovery.setFound(Found.GROUND);
        discovery.setRange(10);
        context = new Context();
        context.setLastDiscovery(discovery);
    }

    @Test
    public void testInstance() {
        ScanTheGround scan = ScanTheGround.getInstance();
        assertEquals(scanTheGround, scan);
    }

    @Test
    public void testGetState() {
        AerialState state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(state, ScanTheGround.getInstance());
    }

    @Test
    public void testResponseStateScanTheGround() throws IndexOutOfBoundsComboAction {
        Action act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), new Fly().getClass());
    }

    @Test
    public void testResponseStateReturnBack() {
        Biome biome = new Biome("OCEAN");
        Discovery discovery = new Discovery();

        List biomes = new ArrayList<>();
        biomes.add(biome);
        discovery.setBiomes(biomes);
        context.setLastDiscovery(discovery);

        AerialState state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(state, ReturnBack.getInstance());
    }

    @Test
    public void testResponseStateFinish() throws IndexOutOfBoundsComboAction {
        Discovery discovery = new Discovery();

        List<Creek> creeks = new ArrayList<>();
        creeks.add(new Creek("adaet-124fqdfaae-avaer"));
        discovery.setCreeks(creeks);
        context.setLastDiscovery(discovery);

        Action act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(act.getClass(), new Land("adaet-124fqdfaae-avaer", 1).getClass());

        AerialState state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(state, Finish.getInstance());
    }

    @Ignore
    public void testActionEcho() throws IndexOutOfBoundsComboAction {
        Action act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(new Scan().getClass(), act.getClass());

        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(new Echo(context.getHeading()).getClass(), act.getClass());

        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(new Fly().getClass(), act.getClass());
    }
}
