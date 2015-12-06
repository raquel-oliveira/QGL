package fr.unice.polytech.qgl.qab.utilTest;

import fr.unice.polytech.qgl.qab.enums.Found;
import fr.unice.polytech.qgl.qab.util.Discovery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 4.9
 */
public class DiscoveryTest {
    Discovery d;

    @Test
    public void testInicializeGround() {
        d = new Discovery(Found.GROUND, 10);
        assertEquals(Found.GROUND, d.getFound());
        assertEquals(10, d.getRange());
    }

    @Test
    public void testInicializeOutOfRange() {
        d = new Discovery(Found.OUT_OF_RANGE, 10);
        assertEquals(Found.OUT_OF_RANGE, d.getFound());
        assertEquals(10, d.getRange());
    }

    @Test
    public void testChangeRange() {
        d = new Discovery(Found.OUT_OF_RANGE, 10);
        assertEquals(Found.OUT_OF_RANGE, d.getFound());
        assertEquals(10, d.getRange());

        d.setRange(9);
        assertEquals(9, d.getRange());

        d.setRange(8);
        assertEquals(8, d.getRange());

        d.setRange(7);
        assertEquals(7, d.getRange());

        d.setRange(6);
        assertEquals(6, d.getRange());
    }
}
