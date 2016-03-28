package fr.unice.polytech.qgl.qab.map.zones;

import fr.unice.polytech.qgl.qab.map.tile.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @version 28/03/16.
 */
public class ZoneTest {
    Zone zone;

    @Before
    public void defineContext() {
        zone = new Zone();
    }

    @Test
    public void testGetPosition() throws Exception {

    }

    @Test
    public void testAddPosition() throws Exception {

    }

    @Test
    public void testCreateSimpleZone() throws Exception {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 1));
        positions.add(new Position(3, 1));
        positions.add(new Position(1, 2));
        positions.add(new Position(2, 2));
        positions.add(new Position(3, 2));
        positions.add(new Position(1, 3));
        positions.add(new Position(2, 3));
        positions.add(new Position(3, 3));

        positions.add(new Position(10, 10));
        positions.add(new Position(9, 12));
        positions.add(new Position(10, 9));

        zone.createZone(positions, positions.remove(0));

        assertTrue(zone.getPositions().size() == 9);
    }

    @Test
    public void testCreateBigZone() throws Exception {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 1));
        positions.add(new Position(3, 1));
        positions.add(new Position(1, 2));
        positions.add(new Position(2, 2));
        positions.add(new Position(3, 2));
        positions.add(new Position(1, 3));
        positions.add(new Position(2, 3));
        positions.add(new Position(3, 3));

        positions.add(new Position(4, 1));
        positions.add(new Position(4, 2));
        positions.add(new Position(4, 3));
        positions.add(new Position(5, 1));
        positions.add(new Position(5, 2));
        positions.add(new Position(5, 3));
        positions.add(new Position(6, 1));
        positions.add(new Position(6, 2));
        positions.add(new Position(6, 3));

        positions.add(new Position(10, 10));
        positions.add(new Position(9, 12));
        positions.add(new Position(10, 9));

        zone.createZone(positions, positions.remove(0));

        assertEquals(18, zone.getPositions().size());
    }

    @Test
    public void testIsNeighbor() throws Exception {

    }
}