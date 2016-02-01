package fr.unice.polytech.qgl.qab.mapTest;

import fr.unice.polytech.qgl.qab.map.tile.Biome;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 31/01/16.
 */
public class BiomeTest {
    Biome b;

    @Before
    public void defineContext() {
        b = new Biome();
    }

    @Test
    public void testCreateObject() {
        assertEquals("", b.getName());

        b = new Biome("testBiome");
        assertEquals("testBiome", b.getName());
    }

    @Test
    public void testSetName() {
        b.setName("NewNameBiome");
        assertEquals("NewNameBiome", b.getName());
    }
}
