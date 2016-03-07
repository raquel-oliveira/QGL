package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.simple.common.Land;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.map.tile.Position;
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
import static junit.framework.TestCase.assertTrue;

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
        scanTheGround = new ScanTheGround();
        Discovery discovery = new Discovery();
        context = new Context();
        context.setLastDiscovery(discovery);
    }

    @Test
    public void testInstance() {
        ScanTheGround scan = new ScanTheGround();
        assertEquals(scanTheGround.getClass(), scan.getClass());

        // now, we have two differents instances
        assertTrue(scanTheGround != scan);
    }

    @Test
    public void testGetState() {
        AerialState state = scanTheGround.getState(context, new Map(), StateMediator.getInstance());
        assertEquals(state.getClass(), ScanTheGround.class);
    }

    @Test
    public void testResponseStateScanTheGround() throws IndexOutOfBoundsComboAction {
        context.setHeading(Direction.EAST);
        Map map = new Map();
        map.setLastPosition(new Position(0, 0));
        Action act = scanTheGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(act.getClass(), Fly.class);
    }

    /**
     * the situation to return back to the ground
     * @throws IndexOutOfBoundsComboAction
     */
    @Test
    public void testResponseStateReturnBack() throws IndexOutOfBoundsComboAction {
        setContext(Found.OUT_OF_RANGE, 10);

        context.setLastDiscovery(discovery);
        context.setHeading(Direction.EAST);
        Map map = new Map();
        map.setLastPosition(new Position(0, 0));

        AerialState state = scanTheGround.getState(context, map, StateMediator.getInstance());
        assertEquals(ScanTheGround.class, state.getClass());

        // the firts current will be a fly
        Action act = scanTheGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());
        state = scanTheGround.getState(context, map, StateMediator.getInstance());
        assertEquals(ScanTheGround.class, state.getClass());

        // after the fly we have the scan
        act = scanTheGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(Scan.class, act.getClass());
        state = scanTheGround.getState(context, map, StateMediator.getInstance());
        assertEquals(ScanTheGround.class, state.getClass());

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());
        state = scanTheGround.getState(context, map, StateMediator.getInstance());
        assertEquals(ReturnBack.class, state.getClass());
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

        assertEquals(state.getClass(), ScanTheGround.class);
    }

    /**
     * If the plane is in the ocean, but there is ground in the same direction
     */
    @Test
    public void testFlyUntilGround() throws IndexOutOfBoundsComboAction {
        setContext(Found.GROUND, 2);

        context.setLastDiscovery(discovery);
        context.setLastDiscovery(discovery);
        context.setHeading(Direction.EAST);
        Map map = new Map();
        map.setLastPosition(new Position(0, 0));

        AerialState state = scanTheGround.getState(context, map, StateMediator.getInstance());
        assertEquals(ScanTheGround.class, state.getClass());

        // the firts current will be a fly
        Action act = scanTheGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(Fly.class, act.getClass());
        state = scanTheGround.getState(context, map, StateMediator.getInstance());
        assertEquals(ScanTheGround.class, state.getClass());

        // after the fly we have the scan
        act = scanTheGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(Scan.class, act.getClass());
        state = scanTheGround.getState(context, map, StateMediator.getInstance());
        assertEquals(ScanTheGround.class, state.getClass());

        // after the scan, when we found the ocean, we make a echo
        act = scanTheGround.responseState(context, map, StateMediator.getInstance());
        assertEquals(Echo.class, act.getClass());
        state = scanTheGround.getState(context, map, StateMediator.getInstance());
        assertEquals(FlyUntil.class, state.getClass());
    }
}
