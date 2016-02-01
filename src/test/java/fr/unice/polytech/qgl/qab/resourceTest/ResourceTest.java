package fr.unice.polytech.qgl.qab.resourceTest;

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
        resource = new Resource("FISH");
    }

    @Test
    public void testGoodObject() {
        assertEquals("FISH", resource.getName());

        resource = new Resource("FISH", 10, 10);
        assertEquals("FISH", resource.getName());
        assertEquals(10, resource.getAmount());
        assertEquals(10, resource.getContract());
    }

    @Test
    public void testSets() {
        resource.setAmount(20);
        assertEquals(20, resource.getAmount());

        resource.setContract(20);
        assertEquals(20, resource.getContract());
    }

    @Test
    public void testIncrement() {
        resource.increment(40);
        assertEquals(40, resource.getAmount());
    }
}
