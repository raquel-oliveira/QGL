package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.response.EchoResponse;
import fr.unice.polytech.qgl.qab.response.ScanResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
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
    Biomes biome;
    Discovery discovery;
    ScanResponse scanResponse;
    EchoResponse echoResponse;

    @Before
    public void defineContext() throws NegativeBudgetException {
        scanTheGround = ScanTheGround.getInstance();
        Discovery discovery = new Discovery();
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

    /**
     * the situation to return back to the ground
     * @throws IndexOutOfBoundsComboAction
     */
    @Test
    public void testResponseStateReturnBack() throws IndexOutOfBoundsComboAction {
        setContext(Found.OUT_OF_RANGE, 10);

        context.setLastDiscovery(discovery);

        AerialState state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(ScanTheGround.getInstance(), state);

        // the firts action will be a fly
        Action act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());
        state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(ScanTheGround.getInstance(), state);

        // after the fly we have the scan
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Scan.class, act.getClass());
        state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(ScanTheGround.getInstance(), state);

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());
        state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(ReturnBack.getInstance(), state);
    }

    private void setContext(Found found, int range) {
        biome = Biomes.OCEAN;
        discovery = new Discovery();
        List<Biomes> biomes = new ArrayList<>();
        biomes.add(biome);

        // the response scan found ocean
        scanResponse = new ScanResponse();
        scanResponse.setBiomes(biomes);
        discovery.setScanResponse(scanResponse);

        // the response echo found a out of range
        echoResponse = new EchoResponse();
        echoResponse.addData(found, Direction.EAST, range);
        discovery.setEchoResponse(echoResponse);
    }

    @Test
    public void testResponseStateFinish() throws IndexOutOfBoundsComboAction {
        Discovery discovery = new Discovery();

        List<Creek> creeks = new ArrayList<>();
        creeks.add(new Creek("adaet-124fqdfaae-avaer"));
        discovery.setCreeks(creeks);
        context.setLastDiscovery(discovery);

        AerialState state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(state, Finish.getInstance());
    }

    /**
     * If the plane is in the ocean, but there is ground in the same direction
     */
    @Ignore
    public void testFlyUntilGround() throws IndexOutOfBoundsComboAction {
        setContext(Found.GROUND, 2);

        context.setLastDiscovery(discovery);

        AerialState state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(ScanTheGround.getInstance(), state);

        // the firts action will be a fly
        Action act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());
        state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(ScanTheGround.getInstance(), state);

        // after the fly we have the scan
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());
        state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(ScanTheGround.getInstance(), state);

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());
        state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(ScanTheGround.getInstance(), state);

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Scan.class, act.getClass());

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Scan.class, act.getClass());

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, new Map(), StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());
    }
}
