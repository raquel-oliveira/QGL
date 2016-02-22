package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
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
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 21/02/16.
 */
public class ChoiceASideTest {
    ChoiceASide choiceASide;

    @Before
    public void defineContext() {
        choiceASide = ChoiceASide.getInstance();
    }

    @Test
    public void testInstance() {
        ChoiceASide choice = ChoiceASide.getInstance();
        assertEquals(choiceASide, choice);
    }

    @Test
    public void testActions() throws NegativeBudgetException, PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        Context context = new Context();
        context.setFirstHead(Direction.NORTH);
        context.setHeading(Direction.NORTH);

        GroundState state = choiceASide.getState(context, new Map());
        assertEquals(state, choiceASide);

        Action act = choiceASide.responseState(context, new Map());
        assertEquals(Glimpse.class, act.getClass());

        GlimpseResponse glimpseResponse = new GlimpseResponse();
        glimpseResponse.setAskedRange(4);
        glimpseResponse.setFourthTile(Biomes.OCEAN);
        Discovery discovery = new Discovery();
        discovery.setGlimpseResponse(glimpseResponse);
        context.setLastDiscovery(discovery);

        state = choiceASide.getState(context, new Map());
        assertEquals(state, choiceASide);

        act = choiceASide.responseState(context, new Map());
        assertEquals(Glimpse.class, act.getClass());

        state = choiceASide.getState(context, new Map());
        assertEquals(state, choiceASide);

        act = choiceASide.responseState(context, new Map());
        assertEquals(Glimpse.class, act.getClass());

        glimpseResponse.setFourthTile(Biomes.ALPINE);
        discovery = new Discovery();
        discovery.setGlimpseResponse(glimpseResponse);
        context.setLastDiscovery(discovery);

        state = choiceASide.getState(context, new Map());
        assertEquals(MoveInTheGround.class, state.getClass());

        act = choiceASide.responseState(context, new Map());
        assertEquals(Stop.class, act.getClass());
    }
}
