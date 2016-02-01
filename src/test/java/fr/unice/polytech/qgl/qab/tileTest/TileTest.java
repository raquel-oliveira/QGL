package fr.unice.polytech.qgl.qab.tileTest;

import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @version 4.9
 */
public class TileTest {
    private Tile t;

    @Test
    public void testInitializeTile() {
        t = new Tile();
        assertEquals(TileType.UNDEFINED, t.getType());
    }

    @Test
    public void testInitializeTileWithTypeGround() {
        t = new Tile(TileType.GROUND);
        assertEquals(TileType.GROUND, t.getType());
    }

    @Test
    public void testInitializeTileWithTypeOcean() {
        t = new Tile(TileType.OCEAN);
        assertEquals(TileType.OCEAN, t.getType());
    }

    @Test
    public void testChangeTypeOfTileToGround() {
        t = new Tile();
        assertEquals(TileType.UNDEFINED, t.getType());

        t.setType(TileType.GROUND);
        assertEquals(TileType.GROUND, t.getType());
    }

    @Test
    public void testChangeTypeOfTileToOcean() {
        t = new Tile();
        assertEquals(TileType.UNDEFINED, t.getType());

        t.setType(TileType.OCEAN);
        assertEquals(TileType.OCEAN, t.getType());
    }

    @Test
    public void testChangeTypeOfTileToUndefined() {
        t = new Tile(TileType.OCEAN);
        assertEquals(TileType.OCEAN, t.getType());

        t.setType(TileType.UNDEFINED);
        assertEquals(TileType.UNDEFINED, t.getType());

        t = new Tile(TileType.GROUND);
        assertEquals(TileType.GROUND, t.getType());

        t.setType(TileType.UNDEFINED);
        assertEquals(TileType.UNDEFINED, t.getType());
    }

    @Test(expected = NullPointerException.class)
    public void testUseBadInstance() {
        t.setCreek(new Creek());
        assertEquals(Creek.class, t.getCreek().getClass());
    }

    @Test
    public void testSetCreek() {
        t = new Tile();
        t.setCreek(new Creek());
        assertEquals(Creek.class, t.getCreek().getClass());
    }
}
