package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.response.ScoutResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.ws.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @version 07/03/16.
 */
public class ScoutTileTest {
    ScoutTile scoutTile;

    @Before
    public void defineContext() {
        scoutTile = new ScoutTile();
    }

    @Test
    public void testState() throws NegativeBudgetException, IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        Context context = new Context();
        context.addContract(PrimaryType.FISH.toString(), 10);

        List<PrimaryType> scoutResp = new ArrayList<>();
        scoutResp.add(PrimaryType.FISH);

        ScoutResponse response = new ScoutResponse();
        response.setResources(scoutResp);

        Discovery discovery = new Discovery();
        discovery.setScoutResponse(response);
        context.setLastDiscovery(discovery);

        testAction(Direction.WEST, 1, context);

        testAction(Direction.NORTH, 1, context);

        testAction(Direction.EAST, 2, context);

        testAction(Direction.SOUTH, 2, context);

        testAction(Direction.WEST, 2, context);

        Action action = scoutTile.responseState(context, new Map());
        assertEquals(MoveTo.class, action.getClass());
        assertEquals(Direction.NORTH, action.getDirection());
        action = scoutTile.responseState(context, new Map());
        assertEquals(MoveTo.class, action.getClass());
        assertEquals(Direction.EAST, action.getDirection());

        GroundState state = scoutTile.getState(context, new Map());
        assertEquals(ExploitTile.class, state.getClass());

    }

    private void testAction(Direction dir, int interations, Context context) throws IndexOutOfBoundsComboAction {
        Action action;
        for (int i = 0; i < interations; i++) {
            action = scoutTile.responseState(context, new Map());
            assertEquals(Scout.class, action.getClass());
            action = scoutTile.responseState(context, new Map());
            assertEquals(MoveTo.class, action.getClass());
            assertEquals(dir, action.getDirection());
        }
    }
}
