package fr.unice.polytech.qgl.qab.actionsTest.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboEchos;
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
        combo.defineComboEchos(Direction.EAST);
        assertEquals(combo.get(0).getClass(), Echo.class);
        assertEquals(((Echo) combo.get(0)).getDirection(), Direction.EAST);
        assertEquals(((Echo) combo.get(1)).getDirection(), Direction.NORTH);
        assertEquals(((Echo) combo.get(2)).getDirection(), Direction.SOUTH);
    }

    @Test
    public void defineComboEchosHorizontalWest() throws IndexOutOfBoundsComboAction {
        combo.defineComboEchos(Direction.WEST);
        assertEquals(combo.get(0).getClass(), Echo.class);
        assertEquals(((Echo)combo.get(0)).getDirection(), Direction.WEST);
        assertEquals(((Echo)combo.get(1)).getDirection(), Direction.NORTH);
        assertEquals(((Echo)combo.get(2)).getDirection(), Direction.SOUTH);
    }

    @Test
    public void defineComboEchosVerticalNorth() throws IndexOutOfBoundsComboAction {
        combo.defineComboEchos(Direction.NORTH);
        assertEquals(combo.get(0).getClass(), Echo.class);
        assertEquals(((Echo) combo.get(0)).getDirection(), Direction.NORTH);
        assertEquals(((Echo) combo.get(1)).getDirection(), Direction.WEST);
        assertEquals(((Echo) combo.get(2)).getDirection(), Direction.EAST);
    }

    @Test
    public void defineComboEchosVerticalSouth() throws IndexOutOfBoundsComboAction {
        combo.defineComboEchos(Direction.SOUTH);
        assertEquals(combo.get(0).getClass(), Echo.class);
        assertEquals(((Echo)combo.get(0)).getDirection(), Direction.SOUTH);
        assertEquals(((Echo)combo.get(1)).getDirection(), Direction.WEST);
        assertEquals(((Echo)combo.get(2)).getDirection(), Direction.EAST);
    }
}
