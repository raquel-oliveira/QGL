package fr.unice.polytech.qgl.qab.mapTest;

import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.tile.TileType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 4.9
 */
public class MapTest {
    Map m;

    @Before
    public void defineContext() {
        m = new Map();
    }

    @Test
    public void testInitializaMap() {
        m.initializeMap(10, 10);
        assertEquals(10, m.getColumns());
        assertEquals(10, m.getRows());


        m.initializeMap(20, 20);
        assertEquals(20, m.getColumns());
        assertEquals(20, m.getRows());
    }

    @Test
    public void testTitleUndefined() {
        m.initializeMap(10, 10);
        m.initializeTitleUndefined(0, 0);
        assertEquals(TileType.UNDEFINED, m.getTitleType(0, 0));

        m.initializeTitleUndefined(9, 9);
        assertEquals(TileType.UNDEFINED, m.getTitleType(9, 9));
    }

    @Test
    public void testTitleGround() {
        m.initializeMap(10, 10);
        m.initializeTitleGround(0, 0);
        assertEquals(TileType.GROUND, m.getTitleType(0, 0));
    }

    @Test
    public void testTitleOcean() {
        m.initializeMap(10, 10);
        m.initializeTitleOcean(0, 0);
        assertEquals(TileType.OCEAN, m.getTitleType(0, 0));
    }

    @Test
    public void testChangeTypeTileToGround() {
        m.initializeMap(10, 10);
        m.initializeTitleUndefined(0, 0);
        assertEquals(TileType.UNDEFINED, m.getTitleType(0, 0));

        m.initializeTitleGround(0, 0);
        assertEquals(TileType.GROUND, m.getTitleType(0, 0));
    }

    @Test
    public void testChangeTypeTileToOcean() {
        m.initializeMap(10, 10);
        m.initializeTitleUndefined(0, 0);
        assertEquals(TileType.UNDEFINED, m.getTitleType(0, 0));

        m.initializeTitleOcean(0, 0);
        assertEquals(TileType.OCEAN, m.getTitleType(0, 0));
    }

    @Test
    public void testMapIsEmpty() {
        assertEquals(true, m.isEmpty());
    }

    @Test
    public void testMapIsNotEmpty() {
        m.initializeMap(10, 10);
        assertEquals(false, m.isEmpty());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testChoiceTitleOutOfBound() {
        m.initializeMap(10, 10);
        m.initializeTitleUndefined(10, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testChoiceTitleBeforeInitializeMap() {
        m.initializeTitleUndefined(10, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testGetColumnsBeforeInitializeMap() {
        m.getColumns();
    }
}
