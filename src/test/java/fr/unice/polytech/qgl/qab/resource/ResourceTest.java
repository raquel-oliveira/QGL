package fr.unice.polytech.qgl.qab.resource;

import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.resources.Resource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 01/02/16.
 */
public class ResourceTest {
    Resource resource;

    @Before
    public void defineContext() {
        resource = new PrimaryResource(PrimaryType.FISH);
    }

    @Test
    public void testGoodObject() {
        assertEquals("FISH", resource.getName());

        resource = new PrimaryResource(PrimaryType.FUR);
        assertEquals("FUR", resource.getName());
    }
}
