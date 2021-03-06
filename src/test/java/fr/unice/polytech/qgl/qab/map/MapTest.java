package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import org.junit.Before;
import org.junit.Ignore;
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

    @Test
    public void testInitializeMap() {
        assertEquals(10, m.getHeight());
        assertEquals(10, m.getWidth());

        m.initializeWidthMap(20, true);
        m.initializeHeightMap(20, true);

        assertEquals(30, m.getHeight());
        assertEquals(30, m.getWidth());
    }

    @Test
    public void testTitleUndefined() throws PositionOutOfMapRange {
        position = new Position(0, 0);
        m.initializeTile(position, TileType.UNDEFINED);
        assertEquals(TileType.UNDEFINED, m.getTileType(position));

        position = new Position(9, 9);
        m.initializeTile(position, TileType.UNDEFINED);
        assertEquals(TileType.UNDEFINED, m.getTileType(position));
    }

    @Test
    public void testTitleGround() throws PositionOutOfMapRange {
        m.initializeTile(position, TileType.GROUND);
        assertEquals(TileType.GROUND, m.getTileType(position));
    }

    @Test
    public void testTitleOcean() throws PositionOutOfMapRange {
        m.initializeTile(position, TileType.OCEAN);
        assertEquals(TileType.OCEAN, m.getTileType(position));
    }

    @Test
    public void testChangeTypeTileToGround() throws PositionOutOfMapRange {
        m.initializeTile(position, TileType.UNDEFINED);
        assertEquals(TileType.UNDEFINED, m.getTileType(position));

        m.initializeTile(position, TileType.GROUND);
        assertEquals(TileType.GROUND, m.getTileType(position));
    }

    @Test
    public void testChangeTypeTileToOcean() throws PositionOutOfMapRange {
        m.initializeTile(position, TileType.UNDEFINED);
        assertEquals(TileType.UNDEFINED, m.getTileType(position));

        m.initializeTile(position, TileType.OCEAN);
        assertEquals(TileType.OCEAN, m.getTileType(position));
    }

    @Test(expected = AssertionError.class)
    public void testIdentifyBadReturns() throws PositionOutOfMapRange {
        Map map = mock(Map.class);
        map.initializeTile(position, TileType.UNDEFINED);

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

    @Test(expected = PositionOutOfMapRange.class)
    public void testChoiceTitleOutOfBound() throws PositionOutOfMapRange {
        position = new Position(15, 15);
        m.initializeTile(position, TileType.UNDEFINED);
    }

    @Test
    public void testCreekLand() {
        position = new Position(15, 15);
        m.setCreekLand(position);

        assertEquals(m.getCreekLand().getX(), position.getX());
        assertEquals(m.getCreekLand().getY(), position.getY());
    }
}
