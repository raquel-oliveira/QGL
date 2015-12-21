package fr.unice.polytech.qgl.qab.exceptionTest;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import org.junit.Test;

/**
 * @version 21/12/15.
 */
public class PositionOutOfMapRangeTest {

    Map map;

    @Test(expected = PositionOutOfMapRange.class)
    public void testPositionWithoutInicialize() throws PositionOutOfMapRange {
        map = new Map();
        map.initializeTileGround(new Position(0, 0));
    }

    @Test(expected = PositionOutOfMapRange.class)
    public void testPositionBigger() throws PositionOutOfMapRange {
        map = new Map();
        map.initializeWidthMap(10, true);
        map.initializeHeightMap(10, true);
        map.initializeTileOcean(new Position(11, 14));
    }

    @Test(expected = PositionOutOfMapRange.class)
    public void testPositionNEgative() throws PositionOutOfMapRange {
        map = new Map();
        map.initializeWidthMap(10, true);
        map.initializeHeightMap(10, true);
        map.initializeTileOcean(new Position(-11, -14));
    }
}
