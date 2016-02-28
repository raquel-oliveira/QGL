package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 31/01/16.
 */
public class FlyUntilTest {
    FlyUntil flyUntil;

    @Before
    public void defineContext() {
        flyUntil = FlyUntil.getInstance();
    }

    @Test
    public void testInstance() {
        FlyUntil end = FlyUntil.getInstance();
        assertEquals(FlyUntil.class, end.getClass());
    }

    @Test
    public void testGetState() throws NegativeBudgetException {
        Map map = new Map();
        map.setLastPosition(new Position(0, 0));
        Context context = new Context();
        context.setHeading(Direction.EAST);
        context.current().setComboAction(new ComboFlyUntil());

        AerialState state = flyUntil.getState(context, map, StateMediator.getInstance());
        assertEquals(state.getClass(), ScanTheGround.class);
    }

    @Test
    public void testResponseState() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        Map map = new Map();
        map.setLastPosition(new Position(0, 0));
        Context context = new Context();
        context.setHeading(Direction.EAST);

        StateMediator sm = StateMediator.getInstance();
        sm.setRangeToGround(2);

        Action act = flyUntil.responseState(context, map, sm);
        assertEquals(act.getClass(), Fly.class);

        AerialState as = flyUntil.getState(context, map, sm);
        assertEquals(as.getClass(), FlyUntil.class);

        act = flyUntil.responseState(context, map, sm);
        assertEquals(act.getClass(), Fly.class);

        as = flyUntil.getState(context, map, sm);
        assertEquals(as.getClass(), FlyUntil.class);
    }
}
