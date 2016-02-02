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
        type = TileType.fromString("ocean");
        assertEquals(TileType.OCEAN, type);
    }

    @Test
    public void testFromStringGround() {
        type = TileType.fromString("ground");
        assertTrue(type.isEquals(TileType.GROUND));
    }

    @Test
    public void testFromStringUndefined() {
        type = TileType.fromString("undefined");
        assertTrue(type.isEquals(TileType.UNDEFINED));
    }

    @Test(expected = NullPointerException.class)
    public void testEqualStringNull() {
        type = TileType.fromString("error");
        assertTrue(type.isEquals(null));
    }

    @Test
    public void testFromStringNotExists() {
        type = TileType.fromString("error");
        assertEquals(null, type);
    }

    @Test(expected = NullPointerException.class)
    public void testToStringBeforeInitialize() {
        type.toString();
    }
}
