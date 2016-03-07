package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 07/03/16.
 */
public class ScoutResponseTest {
    ScoutResponse scoutResponse;

    @Before
    public void defineContext() {
        scoutResponse = new ScoutResponse();
    }

    @Test
    public void testAddResource() {
        scoutResponse.setDirection(Direction.WEST);
        assertEquals(Direction.WEST, scoutResponse.getDirection());
    }

    @Test
    public void testSetResources() {
        List<PrimaryType> resources = new ArrayList<>();
        resources.add(PrimaryType.FISH);
        resources.add(PrimaryType.WOOD);
        resources.add(PrimaryType.FRUITS);
        scoutResponse.setResources(resources);

        assertEquals(resources, scoutResponse.getResources());

        assertTrue(scoutResponse.found("FISH"));
        assertTrue(scoutResponse.found("WOOD"));
        assertTrue(scoutResponse.found("FRUITS"));
        assertFalse(scoutResponse.found("FUR"));
    }
}
