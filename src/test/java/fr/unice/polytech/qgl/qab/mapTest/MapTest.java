package fr.unice.polytech.qgl.qab.mapTest;

import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version 4.9
 */
public class MapTest {
    Map m;
    Position position;

    @Before
    public void defineContext() {
        m = new Map();
        position = new Position(9, 9);
    }

    @Test
    public void testInitializaMap() {
        m.initializeMap(10, 10, true, true);
        assertEquals(10, m.getHeigth());
        assertEquals(10, m.getWidth());


        m.initializeMap(20, 20, true, true);
        assertEquals(20, m.getHeigth());
        assertEquals(20, m.getWidth());
    }

    @Test
    public void testTitleUndefined() {
        m.initializeMap(10, 10, true, true);

        position = new Position(0, 0);
        m.initializeTiteUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTiteType(position));

        position = new Position(9, 9);
        m.initializeTiteUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTiteType(position));
    }

    @Test
    public void testTitleGround() {
        m.initializeMap(10, 10, true, true);
        m.initializeTiteGround(position);
        assertEquals(TileType.GROUND, m.getTiteType(position));
    }

    @Test
    public void testTitleOcean() {
        m.initializeMap(10, 10, true, true);
        m.initializeTiteOcean(position);
        assertEquals(TileType.OCEAN, m.getTiteType(position));
    }

    @Test
    public void testChangeTypeTileToGround() {
        m.initializeMap(10, 10, true, true);
        m.initializeTiteUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTiteType(position));

        m.initializeTiteGround(position);
        assertEquals(TileType.GROUND, m.getTiteType(position));
    }

    @Test
    public void testChangeTypeTileToOcean() {
        m.initializeMap(10, 10, true, true);
        m.initializeTiteUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTiteType(position));

        m.initializeTiteOcean(position);
        assertEquals(TileType.OCEAN, m.getTiteType(position));
    }

    // TODO: mockito
    @Test(expected = AssertionError.class)
    public void testIdentifyBadReturns() {
        Map map = mock(Map.class);
        map.initializeMap(10, 10, true, true);
        map.initializeTiteUndefined(position);

        when(map.getTiteType(any())).thenReturn(TileType.GROUND);
        assertEquals(TileType.UNDEFINED, map.getTiteType(position));
    }

    @Test
    public void testMapIsEmpty() {
        assertEquals(true, m.isEmpty());
    }

    @Test
    public void testMapIsNotEmpty() {
        m.initializeMap(10, 10, true, true);
        assertEquals(true, m.isEmpty());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testChoiceTitleOutOfBound() {
        m.initializeMap(10, 10, true, true);
        position = new Position(15, 15);
        m.initializeTiteUndefined(position);
    } 
}
