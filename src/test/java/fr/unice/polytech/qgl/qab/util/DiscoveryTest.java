package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 4.9
 */
public class DiscoveryTest {
    private Discovery d;

    /*@Test
    public void testInitializeGround() {
        d = new Discovery(Found.GROUND, 10, Direction.SOUTH);
        assertEquals(Found.GROUND, d.getFound());
        assertEquals(10, d.getRange());
        assertEquals(Direction.SOUTH, d.getDirection());
    }

    @Test
    public void testInitializeOutOfRange() {
        d = new Discovery(Found.OUT_OF_RANGE, 10, Direction.NORTH);
        assertEquals(Found.OUT_OF_RANGE, d.getFound());
        assertEquals(10, d.getRange());
        assertEquals(Direction.NORTH, d.getDirection());
    }

    @Test
    public void testChangeRange() {
        d = new Discovery(Found.OUT_OF_RANGE, 10, Direction.EAST);
        assertEquals(Found.OUT_OF_RANGE, d.getFound());
        assertEquals(10, d.getRange());
        assertEquals(Direction.EAST, d.getDirection());

        d.setRange(9);
        assertEquals(9, d.getRange());

        d.setRange(8);
        assertEquals(8, d.getRange());

        d.setRange(7);
        assertEquals(7, d.getRange());

        d.setRange(6);
        assertEquals(6, d.getRange());
    }*/
}
