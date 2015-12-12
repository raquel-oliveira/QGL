package fr.unice.polytech.qgl.qab.mapTest;

import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version 12.12.2015
 */
public class TileTest {
    Tile tile;

    @Before
    public void defineContext() {
        tile = new Tile();
    }

    @Test
    public void testSetType() {
        tile.setType(TileType.GROUND);
        assertEquals(tile.getType(), TileType.GROUND);

        tile.setType(TileType.OCEAN);
        assertEquals(tile.getType(), TileType.OCEAN);

        tile.setType(TileType.UNDEFINED);
        assertEquals(tile.getType(), TileType.UNDEFINED);
    }

    @Test
    public void testSetCreek() {
        tile.setCreek(true);
        assertTrue(tile.getCreek());

        tile.setCreek(false);
        assertFalse(tile.getCreek());
    }

    @Test
    public void testVisit() {
        tile.setVisit(true);
        assertTrue(tile.wasVisited());

        tile.setVisit(false);
        assertFalse(tile.wasVisited());
    }

    @Test(expected = AssertionError.class)
    public void testBadReturnTileType() {
        Tile t = mock(Tile.class);
        t.setType(TileType.GROUND);

        when(t.getType()).thenReturn(TileType.OCEAN);
        assertEquals(TileType.UNDEFINED, t.getType());
    }

    @Test(expected = AssertionError.class)
    public void testBadReturnCreek() {
        Tile t = mock(Tile.class);
        t.setCreek(true);

        when(t.getCreek()).thenReturn(false);
        assertEquals(true, t.getCreek());
    }

    @Test(expected = AssertionError.class)
    public void testBadReturnVisit() {
        Tile t = mock(Tile.class);
        t.setVisit(true);

        when(t.wasVisited()).thenReturn(false);
        assertEquals(true, t.wasVisited());
    }
}

