package fr.unice.polytech.qgl.qab.enumsTest;

import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Gabriela
 * @version 4.9
 */
public class DirectionTest {
    Direction direction;

    @Test
    public void testDirectionEast() {
        direction = Direction.EAST;
        assertEquals(Direction.EAST, direction);
    }

    @Test
    public void testDirectionWest() {
        direction = Direction.WEST;
        assertEquals(Direction.WEST, direction);
    }

    @Test
    public void testDirectionNorth() {
        direction = Direction.NORTH;
        assertEquals(Direction.NORTH, direction);
    }

    @Test
    public void testDirectionSouth() {
        direction = Direction.SOUTH;
        assertEquals(Direction.SOUTH, direction);
    }

    @Test
    public void testToStringEast() {
        direction = Direction.EAST;
        assertEquals(Direction.EAST.toString(), direction.toString());
    }

    @Test
    public void testToStringWest() {
        direction = Direction.WEST;
        assertEquals(Direction.WEST.toString(), direction.toString());
    }

    @Test
    public void testToStringNorth() {
        direction = Direction.NORTH;
        assertEquals(Direction.NORTH.toString(), direction.toString());
    }

    @Test
    public void testToStringSouth() {
        direction = Direction.SOUTH;
        assertEquals(Direction.SOUTH.toString(), direction.toString());
    }

    @Test
    public void testFromStringEast() {
        direction = Direction.fromString("e");
        assertEquals(Direction.EAST, direction);
    }

    @Test
    public void testFromStringWest() {
        direction = Direction.fromString("w");
        assertEquals(Direction.WEST, direction);
    }

    @Test
    public void testFromStringNorth() {
        direction = Direction.fromString("n");
        assertEquals(Direction.NORTH, direction);
    }

    @Test
    public void testFromStringSouth() {
        direction = Direction.fromString("s");
        assertEquals(Direction.SOUTH, direction);
    }

    @Test
    public void testIsVerticalSouth() {
        direction = Direction.SOUTH;
        assertEquals(true, direction.isVertical());
    }

    @Test
    public void testIsVerticalNorth() {
        direction = Direction.NORTH;
        assertEquals(true, direction.isVertical());
    }

    @Test
    public void testIsHorizontalWest() {
        direction = Direction.WEST;
        assertEquals(true, direction.isHorizontal());
    }

    @Test
    public void testIsHorizontalEast() {
        direction = Direction.EAST;
        assertEquals(true, direction.isHorizontal());
    }

    @Test
    public void testFromStringNotExists() {
        direction = Direction.fromString("error");
        assertEquals(null, direction);
    }

    @Test(expected = NullPointerException.class)
    public void testToStringBeforeInitialize() {
        direction.toString();
    }

    @Test
    public void testToStringBeforeInitialize2() {
        try {
            direction.toString();
            fail();
        } catch (NullPointerException e) {}
    }
}
