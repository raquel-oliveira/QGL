package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @version 27/03/16.
 */
public class MapHandlerTest {
    private Map map;
    private HandlerMap mapHandler;


    @Before
    public void defineContext() {
        this.map = new Map();
        map.initializeWidthMap(10, false);
        map.initializeHeightMap(10, false);

        mapHandler = new HandlerMap();
    }

    @Test
    public void testCompletMap() throws Exception {
        assertEquals(10, map.getHeight());
        assertEquals(10, map.getWidth());

        List<Biomes> biomes = new ArrayList<>();
        biomes.add(Biomes.BEACH);

        map.addBiome(new Position(1, 1), biomes, new ArrayList<>());
        map.addBiome(new Position(1, 3), biomes, new ArrayList<>());
        map.addBiome(new Position(3, 1), biomes, new ArrayList<>());
        map.addBiome(new Position(3, 3), biomes, new ArrayList<>());

        mapHandler.completMap(map);

        assertTrue(map.getTileOverride(new Position(2, 2)) != null);
        assertEquals(biomes, map.getTileOverride(new Position(2, 2)).getBiomesPredominant());

        assertTrue(map.getTileOverride(new Position(1, 2)) == null);
        assertTrue(map.getTileOverride(new Position(2, 1)) == null);
        assertTrue(map.getTileOverride(new Position(3, 2)) == null);
        assertTrue(map.getTileOverride(new Position(2, 3)) == null);
        assertEquals(5, map.getTiles().size());

        mapHandler.completMap(map);

        assertTrue(map.getTileOverride(new Position(2, 2)) != null);
        assertEquals(biomes, map.getTileOverride(new Position(2, 2)).getBiomesPredominant());

        assertTrue(map.getTileOverride(new Position(1, 2)) == null);
        assertTrue(map.getTileOverride(new Position(2, 1)) == null);
        assertTrue(map.getTileOverride(new Position(3, 2)) == null);
        assertTrue(map.getTileOverride(new Position(2, 3)) == null);
        assertEquals(5, map.getTiles().size());
    }

    @Test
    public void testCompletMapSeconde() throws Exception {
        List<Biomes> biomes = new ArrayList<>();
        biomes.add(Biomes.BEACH);

        map.addBiome(new Position(2, 2), biomes, new ArrayList<>());
        map.addBiome(new Position(3, 3), biomes, new ArrayList<>());
        map.addBiome(new Position(2, 4), biomes, new ArrayList<>());
        map.addBiome(new Position(1, 3), biomes, new ArrayList<>());

        mapHandler.completMap(map);

        assertTrue(map.getTileOverride(new Position(2, 3)) != null);
        assertEquals(biomes, map.getTileOverride(new Position(2, 2)).getBiomesPredominant());
    }
}