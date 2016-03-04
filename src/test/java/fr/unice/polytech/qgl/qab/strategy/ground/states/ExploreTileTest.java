package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
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
    public void testActions() throws NegativeBudgetException, PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        GroundState state = exploreTile.getState(new Context(), new Map());
        assertEquals(ScoutTile.class, state.getClass());

        List<PrimaryType> resourcesToExploit = new ArrayList<>();
        resourcesToExploit.add(PrimaryType.FISH);
        Context context = new Context();
        context.current().setResourcesToExploit(resourcesToExploit, context);

        Action act = exploreTile.responseState(new Context(), new Map());
        assertEquals(Explore.class, act.getClass());
    }
}
