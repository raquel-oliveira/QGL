package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @version 27/12/15.
 */
public class ComboEchosTest {
    ComboEchos combo;

    @Before
    public void defineContext() {
        combo = new ComboEchos();
    }

    @Test
    public void defineComboEchosHorizontalEast() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.EAST);
        assertEquals(combo.get(0).getClass(), Echo.class);
        assertEquals((combo.get(0)).getDirection(), Direction.EAST);
        assertEquals((combo.get(1)).getDirection(), Direction.SOUTH);
        assertEquals((combo.get(2)).getDirection(), Direction.NORTH);
    }

    @Test
    public void defineComboEchosHorizontalWest() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.WEST);
        assertEquals(combo.get(0).getClass(), Echo.class);
        assertEquals((combo.get(0)).getDirection(), Direction.WEST);
        assertEquals((combo.get(1)).getDirection(), Direction.SOUTH);
        assertEquals((combo.get(2)).getDirection(), Direction.NORTH);
    }

    @Test
    public void defineComboEchosVerticalNorth() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.NORTH);
        assertEquals(combo.get(0).getClass(), Echo.class);
        assertEquals((combo.get(0)).getDirection(), Direction.NORTH);
        assertEquals((combo.get(1)).getDirection(), Direction.EAST);
        assertEquals((combo.get(2)).getDirection(), Direction.WEST);
    }

    @Test
    public void defineComboEchosVerticalSouth() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.SOUTH);
        assertEquals(combo.get(0).getClass(), Echo.class);
        assertEquals((combo.get(0)).getDirection(), Direction.SOUTH);
        assertEquals((combo.get(1)).getDirection(), Direction.EAST);
        assertEquals((combo.get(2)).getDirection(), Direction.WEST);
    }
}
