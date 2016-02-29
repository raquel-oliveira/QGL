package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
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
    public void testAction() throws IndexOutOfBoundsComboAction {
        testActionInCombo(Direction.EAST, Direction.SOUTH);
        testActionInCombo(Direction.EAST, Direction.NORTH);

        testActionInCombo(Direction.WEST, Direction.SOUTH);
        testActionInCombo(Direction.WEST, Direction.NORTH);

        testActionInCombo(Direction.NORTH, Direction.WEST);
        testActionInCombo(Direction.NORTH, Direction.EAST);

        testActionInCombo(Direction.SOUTH, Direction.WEST);
        testActionInCombo(Direction.SOUTH, Direction.EAST);
    }

    public void testActionInCombo(Direction d1, Direction d2) throws IndexOutOfBoundsComboAction {
        combo.defineActions(d1, d2);
        assertEquals(combo.get(0).getClass(), Heading.class);
        assertEquals(combo.get(1).getClass(), Heading.class);

        assertEquals(combo.get(2).getClass(), Echo.class);

        assertEquals(combo.get(3).getClass(), Heading.class);
        assertEquals(combo.get(4).getClass(), Heading.class);

        assertEquals(combo.get(5).getClass(), Echo.class);
    }

    @Test
    public void testActionDirectionWestNorth() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.WEST, Direction.NORTH);
        assertEquals(combo.get(0).getDirection(), Direction.NORTH);
        assertEquals(combo.get(1).getDirection(), Direction.EAST);
        assertEquals(combo.get(3).getDirection(), Direction.NORTH);
        assertEquals(combo.get(4).getDirection(), Direction.WEST);
    }

    @Test
    public void testActionDirectionWestSouth() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.WEST, Direction.SOUTH);
        assertEquals(combo.get(0).getDirection(), Direction.SOUTH);
        assertEquals(combo.get(1).getDirection(), Direction.EAST);
        assertEquals(combo.get(3).getDirection(), Direction.SOUTH);
        assertEquals(combo.get(4).getDirection(), Direction.WEST);
    }

    @Test
    public void testActionDirectionEastNorth() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.EAST, Direction.NORTH);
        assertEquals(combo.get(0).getDirection(), Direction.NORTH);
        assertEquals(combo.get(1).getDirection(), Direction.WEST);
        assertEquals(combo.get(3).getDirection(), Direction.NORTH);
        assertEquals(combo.get(4).getDirection(), Direction.EAST);
    }

    @Test
    public void testActionDirectionEastSouth() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.EAST, Direction.SOUTH);
        assertEquals(combo.get(0).getDirection(), Direction.SOUTH);
        assertEquals(combo.get(1).getDirection(), Direction.WEST);
        assertEquals(combo.get(3).getDirection(), Direction.SOUTH);
        assertEquals(combo.get(4).getDirection(), Direction.EAST);
    }

    @Test
    public void testActionDirectionSouthEast() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.SOUTH, Direction.EAST);
        assertEquals(combo.get(0).getDirection(), Direction.EAST);
        assertEquals(combo.get(1).getDirection(), Direction.NORTH);
        assertEquals(combo.get(3).getDirection(), Direction.EAST);
        assertEquals(combo.get(4).getDirection(), Direction.SOUTH);
    }

    @Test
    public void testActionDirectionSouthWest() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.SOUTH, Direction.WEST);
        assertEquals(combo.get(0).getDirection(), Direction.WEST);
        assertEquals(combo.get(1).getDirection(), Direction.NORTH);
        assertEquals(combo.get(3).getDirection(), Direction.WEST);
        assertEquals(combo.get(4).getDirection(), Direction.SOUTH);
    }

    @Test
    public void testActionDirectionNorthEast() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.NORTH, Direction.EAST);
        assertEquals(combo.get(0).getDirection(), Direction.EAST);
        assertEquals(combo.get(1).getDirection(), Direction.SOUTH);
        assertEquals(combo.get(3).getDirection(), Direction.EAST);
        assertEquals(combo.get(4).getDirection(), Direction.NORTH);
    }

    @Test
    public void testActionDirectionNorthWest() throws IndexOutOfBoundsComboAction {
        combo.defineActions(Direction.NORTH, Direction.WEST);
        assertEquals(combo.get(0).getDirection(), Direction.WEST);
        assertEquals(combo.get(1).getDirection(), Direction.SOUTH);
        assertEquals(combo.get(3).getDirection(), Direction.WEST);
        assertEquals(combo.get(4).getDirection(), Direction.NORTH);
    }
}
