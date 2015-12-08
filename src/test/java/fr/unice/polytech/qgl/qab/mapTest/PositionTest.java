package fr.unice.polytech.qgl.qab.mapTest;

import fr.unice.polytech.qgl.qab.map.tile.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gabriela on 07/12/15.
 */
public class PositionTest {
    Position p;

    @Test
    public void testInitialize() {
        p = new Position(10, 10);
        assertEquals(p.getX(), 10);
        assertEquals(p.getY(), 10);
    }
}
