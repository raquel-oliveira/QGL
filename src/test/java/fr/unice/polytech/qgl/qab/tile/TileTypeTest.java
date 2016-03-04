package fr.unice.polytech.qgl.qab.tile;

import fr.unice.polytech.qgl.qab.map.tile.TileType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Gabriela
 * @version 4.9
 */
public class TileTypeTest {

    private TileType type;

    @Test
    public void testTileTypeGround() {
        type = TileType.GROUND;
        assertEquals(TileType.GROUND, type);
    }

    @Test
    public void testTileTypeOcean() {
        type = TileType.OCEAN;
        assertEquals(TileType.OCEAN, type);
    }

    @Test
    public void testTileTypeUndefined() {
        type = TileType.UNDEFINED;
        assertEquals(TileType.UNDEFINED, type);
    }

    @Test
    public void testToStringUndefined() {
        type = TileType.UNDEFINED;
        assertEquals(TileType.UNDEFINED.toString(), type.toString());
    }

    @Test
    public void testToStringGround() {
        type = TileType.GROUND;
        assertEquals(TileType.GROUND.toString(), type.toString());
    }

    @Test
    public void testToStringOcean() {
        type = TileType.OCEAN;
        assertEquals(TileType.OCEAN.toString(), type.toString());
    }

    @Test
    public void testFromStringOcean() {
        type = TileType.valueOf("OCEAN");
        assertEquals(TileType.OCEAN, type);
    }

    @Test
    public void testFromStringGround() {
        type = TileType.valueOf("GROUND");
        assertTrue(type.equals(TileType.GROUND));
    }

    @Test
    public void testFromStringUndefined() {
        type = TileType.valueOf("UNDEFINED");
        assertTrue(type.equals(TileType.UNDEFINED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqualStringNull() {
        type = TileType.valueOf("error");
        assertTrue(type.equals(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringNotExists() {
        type = TileType.valueOf("error");
        assertEquals(null, type);
    }

    @Test(expected = NullPointerException.class)
    public void testToStringBeforeInitialize() {
        type.toString();
    }
}
