package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Creek;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 07/03/16.
 */
public class CreekTest {
    Creek creek;

    @Before
    public void defineContext() {
        creek = new Creek();
    }

    @Test
    public void testCreek() {
        assertEquals(null, creek.getIdCreek());
        creek = new Creek("ID");
        assertEquals("ID", creek.getIdCreek());
    }
}
