package fr.unice.polytech.qgl.qab.actionsTest.combo;

import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboReturn;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 27/12/15.
 */
public class ComboReturnTest {
    ComboReturn combo;

    @Before
    public void defineContext() {
        combo = new ComboReturn();
    }

    @Test
    public void testActionInCombo() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.EAST, Direction.SOUTH);
        assertEquals(combo.get(0).getClass(), Heading.class);
        assertEquals(combo.get(1).getClass(), Heading.class);

        assertEquals(combo.get(2).getClass(), Echo.class);

        assertEquals(combo.get(3).getClass(), Heading.class);
        assertEquals(combo.get(4).getClass(), Heading.class);

        assertEquals(combo.get(5).getClass(), Echo.class);
    }

    @Test
    public void testActionDirectionWestNorth() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.WEST, Direction.NORTH);
        assertEquals(((Heading)combo.get(0)).getDirection(), Direction.NORTH);
        assertEquals(((Heading)combo.get(1)).getDirection(), Direction.EAST);
        assertEquals(((Heading)combo.get(3)).getDirection(), Direction.NORTH);
        assertEquals(((Heading)combo.get(4)).getDirection(), Direction.WEST);
    }

    @Test
    public void testActionDirectionWestSouth() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.WEST, Direction.SOUTH);
        assertEquals(((Heading)combo.get(0)).getDirection(), Direction.SOUTH);
        assertEquals(((Heading)combo.get(1)).getDirection(), Direction.EAST);
        assertEquals(((Heading)combo.get(3)).getDirection(), Direction.SOUTH);
        assertEquals(((Heading)combo.get(4)).getDirection(), Direction.WEST);
    }

    @Test
    public void testActionDirectionEastNorth() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.EAST, Direction.NORTH);
        assertEquals(((Heading)combo.get(0)).getDirection(), Direction.NORTH);
        assertEquals(((Heading)combo.get(1)).getDirection(), Direction.WEST);
        assertEquals(((Heading)combo.get(3)).getDirection(), Direction.NORTH);
        assertEquals(((Heading)combo.get(4)).getDirection(), Direction.EAST);
    }

    @Test
    public void testActionDirectionEastSouth() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.EAST, Direction.SOUTH);
        assertEquals(((Heading)combo.get(0)).getDirection(), Direction.SOUTH);
        assertEquals(((Heading)combo.get(1)).getDirection(), Direction.WEST);
        assertEquals(((Heading)combo.get(3)).getDirection(), Direction.SOUTH);
        assertEquals(((Heading)combo.get(4)).getDirection(), Direction.EAST);
    }

    @Test
    public void testActionDirectionSouthEast() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.SOUTH, Direction.EAST);
        assertEquals(((Heading)combo.get(0)).getDirection(), Direction.EAST);
        assertEquals(((Heading)combo.get(1)).getDirection(), Direction.NORTH);
        assertEquals(((Heading)combo.get(3)).getDirection(), Direction.EAST);
        assertEquals(((Heading)combo.get(4)).getDirection(), Direction.SOUTH);
    }

    @Test
    public void testActionDirectionSouthWest() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.SOUTH, Direction.WEST);
        assertEquals(((Heading)combo.get(0)).getDirection(), Direction.WEST);
        assertEquals(((Heading)combo.get(1)).getDirection(), Direction.NORTH);
        assertEquals(((Heading)combo.get(3)).getDirection(), Direction.WEST);
        assertEquals(((Heading)combo.get(4)).getDirection(), Direction.SOUTH);
    }

    @Test
    public void testActionDirectionNorthEast() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.NORTH, Direction.EAST);
        assertEquals(((Heading)combo.get(0)).getDirection(), Direction.EAST);
        assertEquals(((Heading)combo.get(1)).getDirection(), Direction.SOUTH);
        assertEquals(((Heading)combo.get(3)).getDirection(), Direction.EAST);
        assertEquals(((Heading)combo.get(4)).getDirection(), Direction.NORTH);
    }

    @Test
    public void testActionDirectionNorthWest() throws IndexOutOfBoundsComboAction {
        combo.defineHeading(Direction.NORTH, Direction.WEST);
        assertEquals(((Heading)combo.get(0)).getDirection(), Direction.WEST);
        assertEquals(((Heading)combo.get(1)).getDirection(), Direction.SOUTH);
        assertEquals(((Heading)combo.get(3)).getDirection(), Direction.WEST);
        assertEquals(((Heading)combo.get(4)).getDirection(), Direction.NORTH);
    }
}
