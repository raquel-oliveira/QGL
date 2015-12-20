package fr.unice.polytech.qgl.qab.mapTest;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;
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
    private Map m;
    private Position position;

    @Before
    public void defineContext() {
        m = new Map();
        m.initializeWidthMap(10, false);
        m.initializeHeightMap(10, false);
        position = new Position(9, 9);
    }

    @Test // TODO: this method
    public void testInitializeMap() {
        assertEquals(10, m.getHeight());
        assertEquals(10, m.getWidth());

        m.initializeWidthMap(20, true);
        m.initializeHeightMap(20, true);
        assertEquals(31, m.getHeight());
        assertEquals(31, m.getWidth());
    }

    @Test
    public void testTitleUndefined() throws PositionOutOfMapaRange {
        position = new Position(0, 0);
        m.initializeTileUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTileType(position));

        position = new Position(9, 9);
        m.initializeTileUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTileType(position));
    }

    @Test
    public void testTitleGround() throws PositionOutOfMapaRange {
        m.initializeTileGround(position);
        assertEquals(TileType.GROUND, m.getTileType(position));
    }

    @Test
    public void testTitleOcean() throws PositionOutOfMapaRange {
        m.initializeTileOcean(position);
        assertEquals(TileType.OCEAN, m.getTileType(position));
    }

    @Test
    public void testChangeTypeTileToGround() throws PositionOutOfMapaRange {
        m.initializeTileUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTileType(position));

        m.initializeTileGround(position);
        assertEquals(TileType.GROUND, m.getTileType(position));
    }

    @Test
    public void testChangeTypeTileToOcean() throws PositionOutOfMapaRange {
        m.initializeTileUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTileType(position));

        m.initializeTileOcean(position);
        assertEquals(TileType.OCEAN, m.getTileType(position));
    }

    // TODO: mockito
    @Test(expected = AssertionError.class)
    public void testIdentifyBadReturns() throws PositionOutOfMapaRange {
        Map map = mock(Map.class);
        map.initializeTileUndefined(position);

        when(map.getTileType(any())).thenReturn(TileType.GROUND);
        assertEquals(TileType.UNDEFINED, map.getTileType(position));
    }

    @Test
    public void testMapIsEmpty() {
        assertEquals(true, m.isEmpty());
    }

    @Test
    public void testMapIsNotEmpty() {
        assertEquals(true, m.isEmpty());
    }

    @Test(expected = PositionOutOfMapaRange.class)
    public void testChoiceTitleOutOfBound() throws PositionOutOfMapaRange {
        position = new Position(15, 15);
        m.initializeTileUndefined(position);
    }
}
