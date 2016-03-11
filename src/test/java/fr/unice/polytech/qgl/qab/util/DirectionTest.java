package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 07/03/16.
 */
public class DirectionTest {
    Direction direction;

    @Before
    public void defineContext() {
        direction = null;
    }

    @Test
    public void testInverse() {
        direction = Direction.WEST;
        assertEquals(Direction.EAST, Direction.inverse(direction));

        direction = Direction.EAST;
        assertEquals(Direction.WEST, Direction.inverse(direction));

        direction = Direction.NORTH;
        assertEquals(Direction.SOUTH, Direction.inverse(direction));

        direction = Direction.SOUTH;
        assertEquals(Direction.NORTH, Direction.inverse(direction));
    }
}
