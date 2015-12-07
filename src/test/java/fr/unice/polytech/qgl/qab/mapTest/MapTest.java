package fr.unice.polytech.qgl.qab.mapTest;

import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        m.initializeMap(10, 10);
        assertEquals(10, m.getHeigth());
        assertEquals(10, m.getWitdh());


        m.initializeMap(20, 20);
        assertEquals(20, m.getHeigth());
        assertEquals(20, m.getWitdh());
    }

    @Test
    public void testTitleUndefined() {
        m.initializeMap(10, 10);

        position = new Position(0, 0);
        m.initializeTitleUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTitleType(position));

        position = new Position(9, 9);
        m.initializeTitleUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTitleType(position));
    }

    @Test
    public void testTitleGround() {
        m.initializeMap(10, 10);
        m.initializeTitleGround(position);
        assertEquals(TileType.GROUND, m.getTitleType(position));
    }

    @Test
    public void testTitleOcean() {
        m.initializeMap(10, 10);
        m.initializeTitleOcean(position);
        assertEquals(TileType.OCEAN, m.getTitleType(position));
    }

    @Test
    public void testChangeTypeTileToGround() {
        m.initializeMap(10, 10);
        m.initializeTitleUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTitleType(position));

        m.initializeTitleGround(position);
        assertEquals(TileType.GROUND, m.getTitleType(position));
    }

    @Test
    public void testChangeTypeTileToOcean() {
        m.initializeMap(10, 10);
        m.initializeTitleUndefined(position);
        assertEquals(TileType.UNDEFINED, m.getTitleType(position));

        m.initializeTitleOcean(position);
        assertEquals(TileType.OCEAN, m.getTitleType(position));
    }

    @Test
    public void testMapIsEmpty() {
        assertEquals(true, m.isEmpty());
    }

    @Test
    public void testMapIsNotEmpty() {
        m.initializeMap(10, 10);
        assertEquals(true, m.isEmpty());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testChoiceTitleOutOfBound() {
        m.initializeMap(10, 10);
        position = new Position(15, 15);
        m.initializeTitleUndefined(position);
    } 
}
