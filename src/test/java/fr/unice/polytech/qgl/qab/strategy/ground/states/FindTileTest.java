package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @version 06/03/16.
 */
public class FindTileTest {
    FindTile findTile;
    Context context;
    Map map;

    @Before
    public void defineContext() throws NegativeBudgetException, PositionOutOfMapRange {
        findTile = new FindTile();
        context = new Context();
        map = new Map();
        setContext();
        setMap();
    }

    public void setContext() throws NegativeBudgetException {
        context.current().setStatus(0);
        context.addContract("FISH", 1000);
    }

    public void setMap() throws PositionOutOfMapRange {
        List<Biomes> list = new ArrayList<>();
        list.add(Biomes.OCEAN);

        map.initializeWidthMap(10, true);
        map.initializeHeightMap(10, true);

        map.addBiome(new Position(1, 1), list, new ArrayList<>());
        map.addBiome(new Position(2, 2), list, new ArrayList<>());
        map.addBiome(new Position(3, 3), list, new ArrayList<>());
        map.addBiome(new Position(4, 4), list, new ArrayList<>());
        map.addBiome(new Position(5, 5), list, new ArrayList<>());
        map.addBiome(new Position(6, 6), list, new ArrayList<>());
        map.addBiome(new Position(7, 7), list, new ArrayList<>());
        map.addBiome(new Position(8, 8), list, new ArrayList<>());

        map.setLastPositionGround(new Position(0, 0));
    }

    @Test
    public void testFindTile() throws IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        Action act = findTile.responseState(context, map);
        assertEquals(MoveTo.class, act.getClass());
        assertEquals(Direction.EAST, act.getDirection());

        GroundState state = findTile.getState(context, map);
        assertEquals(FindTile.class, state.getClass());

        act = findTile.responseState(context, map);
        assertEquals(MoveTo.class, act.getClass());
        assertEquals(Direction.EAST, act.getDirection());

        state = findTile.getState(context, map);
        assertEquals(FindTile.class, state.getClass());

        act = findTile.responseState(context, map);
        assertEquals(MoveTo.class, act.getClass());
        assertEquals(Direction.EAST, act.getDirection());

        state = findTile.getState(context, map);
        assertEquals(FindTile.class, state.getClass());

        act = findTile.responseState(context, map);
        assertEquals(MoveTo.class, act.getClass());
        assertEquals(Direction.SOUTH, act.getDirection());

        state = findTile.getState(context, map);
        assertEquals(FindTile.class, state.getClass());

        act = findTile.responseState(context, map);
        assertEquals(MoveTo.class, act.getClass());
        assertEquals(Direction.SOUTH, act.getDirection());

        state = findTile.getState(context, map);
        assertEquals(FindTile.class, state.getClass());

        act = findTile.responseState(context, map);
        assertEquals(MoveTo.class, act.getClass());
        assertEquals(Direction.SOUTH, act.getDirection());

    }
}
