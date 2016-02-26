package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @version 30/01/16.
 * TODO: review this test
 */
public class MoveInTheGroundTest {
    GlimpseTheGround moveInTheGround;

    @Before
    public void defineContext() {
        moveInTheGround = GlimpseTheGround.getInstance();
    }

    @Ignore
    public void testInstance() {
        GlimpseTheGround move = GlimpseTheGround.getInstance();
        assertEquals(moveInTheGround, move);
    }

    @Ignore
    public void testGetState() throws NegativeBudgetException, PositionOutOfMapRange {
        Context context = new Context();
        Discovery discovery = new Discovery();
        discovery.setGlimpseResponse(new GlimpseResponse());
        context.setLastDiscovery(discovery);

        GroundState state = moveInTheGround.getState(context, new Map());
        assertEquals(state, moveInTheGround);

        context.addContract("FISH", 10);
        GlimpseResponse gr = new GlimpseResponse();
        HashMap<Biomes, Double> b = new HashMap<>();
        b.put(Biomes.OCEAN, 50.0);
        List<HashMap<Biomes, Double>> initial = new ArrayList<>();
        initial.add(b);
        initial.add(b);
        gr.setInitialTiles(initial);
        discovery.setGlimpseResponse(gr);
        context.setLastDiscovery(discovery);

        state = moveInTheGround.getState(context, new Map());
        assertEquals(state, ExploreTile.getInstance());
    }

    @Test
    public void testResponseStateStop() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        Context context = new Context();
        Discovery discovery = new Discovery();
        discovery.setGlimpseResponse(new GlimpseResponse());
        context.setLastDiscovery(discovery);

        GlimpseResponse gr = new GlimpseResponse();
        HashMap<Biomes, Double> b = new HashMap<>();
        b.put(Biomes.OCEAN, 100.0);
        List<HashMap<Biomes, Double>> initial = new ArrayList<>();
        initial.add(b);
        initial.add(b);
        gr.setInitialTiles(initial);
        discovery.setGlimpseResponse(gr);
        context.setLastDiscovery(discovery);

        Action act = moveInTheGround.responseState(context, new Map());
        assertEquals(act.getClass(), new Glimpse(Direction.NORTH, 4).getClass());
    }

    @Ignore
    public void testResponseStateGlimpse() throws NegativeBudgetException, IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        Context context = new Context();
        context.setHeading(Direction.EAST);
        Discovery discovery = new Discovery();
        discovery.setGlimpseResponse(new GlimpseResponse());
        context.setLastDiscovery(discovery);

        Action act = moveInTheGround.responseState(context, new Map());
        assertEquals(act.getClass(), new MoveTo(context.getHeading()).getClass());

        act = moveInTheGround.responseState(context, new Map());
        assertEquals(act.getClass(), new Glimpse(context.getHeading(), 4).getClass());

        act = moveInTheGround.responseState(context, new Map());
        assertEquals(act.getClass(), new MoveTo(context.getHeading()).getClass());

        GlimpseResponse gr = new GlimpseResponse();
        HashMap<Biomes, Double> b = new HashMap<>();
        b.put(Biomes.OCEAN, 50.0);
        List<HashMap<Biomes, Double>> initial = new ArrayList<>();
        initial.add(b);
        initial.add(b);
        gr.setInitialTiles(initial);
        discovery.setGlimpseResponse(gr);
        context.setLastDiscovery(discovery);

        GroundState state = moveInTheGround.getState(context, new Map());
        assertEquals(state, GlimpseTheGround.getInstance());

        act = moveInTheGround.responseState(context, new Map());
        assertEquals(act.getClass(), new Glimpse(context.getHeading(), 4).getClass());
    }
}
