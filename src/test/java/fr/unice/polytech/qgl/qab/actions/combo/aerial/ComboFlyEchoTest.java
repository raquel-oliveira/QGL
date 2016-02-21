package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @version 27/12/15.
 */
public class ComboFlyEchoTest {
    ComboFlyEcho combo;

    @Before
    public void defineContext() {
        combo = new ComboFlyEcho();
    }

    @Test
    public void defineComboEchosHorizontal() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.EAST);
        assertEquals(Fly.class, (combo.get(0)).getClass());

        assertEquals(Echo.class, (combo.get(1)).getClass());
        assertEquals(Direction.EAST, (combo.get(1)).getDirection());

        combo = new ComboFlyEcho();
        combo.defineActions(Direction.WEST);
        assertEquals(Fly.class, (combo.get(0)).getClass());

        assertEquals(Echo.class, (combo.get(1)).getClass());
        assertEquals(Direction.WEST, (combo.get(1)).getDirection());
    }

    @Test
    public void defineComboEchosVertical() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.NORTH);
        assertEquals(Fly.class, (combo.get(0)).getClass());

        assertEquals(Echo.class, (combo.get(1)).getClass());
        assertEquals(Direction.NORTH, (combo.get(1)).getDirection());

        combo = new ComboFlyEcho();
        combo.defineActions(Direction.SOUTH);
        assertEquals(Fly.class, (combo.get(0)).getClass());

        assertEquals(Echo.class, (combo.get(1)).getClass());
        assertEquals(Direction.SOUTH, (combo.get(1)).getDirection());
    }
}
