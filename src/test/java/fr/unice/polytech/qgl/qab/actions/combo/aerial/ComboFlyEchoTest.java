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
        assertEquals(Fly.class, ((Fly)combo.get(0)).getClass());

        assertEquals(Echo.class, ((Echo)combo.get(1)).getClass());
        assertEquals(Direction.NORTH, ((Echo)combo.get(1)).getDirection());
        assertEquals(Echo.class, ((Echo)combo.get(2)).getClass());
        assertEquals(Direction.SOUTH, ((Echo)combo.get(2)).getDirection());
    }

    @Test
    public void defineComboEchosVertical() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.NORTH);
        assertEquals(Fly.class, ((Fly)combo.get(0)).getClass());

        assertEquals(Echo.class, ((Echo)combo.get(1)).getClass());
        assertEquals(Direction.WEST, ((Echo)combo.get(1)).getDirection());
        assertEquals(Echo.class, ((Echo)combo.get(2)).getClass());
        assertEquals(Direction.EAST, ((Echo)combo.get(2)).getDirection());
    }
}
