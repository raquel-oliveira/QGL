package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 07/12/15.
 */
public class PositionTest {
    private Position p;

    @Test
    public void testInitialize() {
        p = new Position(10, 10);
        assertEquals(p.getX(), 10);
        assertEquals(p.getY(), 10);
    }
}
