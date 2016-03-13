package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.response.ExploreResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @version 21/02/16.
 */
public class ExploreTileTest {
    ExploreTile exploreTile;

    @Before
    public void defineContext() {
        exploreTile = new ExploreTile();
    }

    @Test
    public void testInstance() {
        ExploreTile explore = new ExploreTile();
        assertEquals(exploreTile.getClass(), explore.getClass());
    }

    @Test
    public void testActionsContractsComplete() throws NegativeBudgetException, PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        GroundState state = exploreTile.getState(new Context(), new Map());
        assertEquals(ScoutTile.class, state.getClass());

        Action act = exploreTile.responseState(new Context(), new Map());
        assertEquals(Stop.class, act.getClass());
    }

    @Test
    public void testActions() throws NegativeBudgetException, PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        List<PrimaryType> resourcesToExploit = new ArrayList<>();
        resourcesToExploit.add(PrimaryType.FISH);
        Context context = new Context();
        context.current().setResourcesToExploit(resourcesToExploit, context);
        context.addContract("FISH", 10);

        ExploreResponse response = new ExploreResponse();
        List<String> resources = new ArrayList<>();
        resources.add("");
        resources.add("FISH");
        response.addResource(resources);
        Discovery discovery = new Discovery();
        discovery.setExploreResponse(response);
        context.setLastDiscovery(discovery);

        Action act = exploreTile.responseState(context, new Map());
        assertEquals(Explore.class, act.getClass());

        GroundState state = exploreTile.getState(context, new Map());
        assertEquals(ExploitTile.class, state.getClass());
    }
}
