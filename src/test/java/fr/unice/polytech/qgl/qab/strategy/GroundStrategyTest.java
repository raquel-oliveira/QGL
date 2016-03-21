package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.response.*;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.ground.GroundStrategy;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @version 09/03/16.
 */
public class GroundStrategyTest {
    GroundStrategy strategy;
    Context context;
    Discovery discovery;
    Map map;

    @Before
    public void defineContext() throws NegativeBudgetException {
        strategy = new GroundStrategy();
        discovery = new Discovery();
        context = new Context();
        map = new Map();
    }

    @Test
    public void testStrategy() throws NegativeBudgetException, IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        setContext();
        setDiscovery();

        context.setLastDiscovery(discovery);

        Action action = strategy.makeDecision(context, new Map());
        assertEquals(Stop.class, action.getClass());

        context.setBudget(1000);

        action = strategy.makeDecision(context, new Map());
        assertEquals(Stop.class, action.getClass());

        setMap();

        action = strategy.makeDecision(context, map);
        assertEquals(Stop.class, action.getClass());

        context.addContract("FISH", 10);
        testMoveTo(Direction.EAST, 9);
        testMoveTo(Direction.SOUTH, 9);

        action = strategy.makeDecision(context, map);
        assertEquals(Scout.class, action.getClass());

        action = strategy.makeDecision(context, map);
        assertEquals(MoveTo.class, action.getClass());
    }

    private void testMoveTo(Direction direction, int range) throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        Action action;
        while (range-- > 0) {
            action = strategy.makeDecision(context, map);
            assertEquals(MoveTo.class, action.getClass());
            assertEquals(direction, action.getDirection());
        }
    }

    private void setDiscovery() {
        discovery.setCreeks(new ArrayList<>());
        discovery.setEchoResponse(new EchoResponse());
        discovery.setGlimpseResponse(new GlimpseResponse());
        discovery.setExploiteResponse(new ExploitResponse());
        discovery.setExploreResponse(new ExploreResponse());
        discovery.setScanResponse(new ScanResponse());
    }

    private void setContext() throws NegativeBudgetException {
        context.setHeading(Direction.EAST);
        context.setFirstHead(Direction.EAST);
        context.setBudget(100);
        context.setMen(10);
        context.setStatus(true);
    }

    private void setMap() {
        List<Biomes> list = new ArrayList<>();
        list.add(Biomes.OCEAN);

        map.initializeWidthMap(10, true);
        map.initializeHeightMap(10, true);

        map.addBiome(new Position(3, 3), list, new ArrayList<>());
        map.addBiome(new Position(4, 4), list, new ArrayList<>());
        map.addBiome(new Position(5, 5), list, new ArrayList<>());
        map.addBiome(new Position(6, 6), list, new ArrayList<>());

        map.setLastPositionGround(new Position(0, 0));
    }
}