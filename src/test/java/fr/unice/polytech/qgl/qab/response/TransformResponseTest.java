package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * version 14/03/2016.
 */
public class TransformResponseTest {
    TransformResponse response;

    @Before
    public void defineContext() {
        response = new TransformResponse();
    }

    @Test
    public void testInitialize() {
        assertEquals(null, response.getResource());
        assertEquals(0, response.getAmount());

        response.addData(new ManufacturedResource(ManufacturedType.GLASS), 10);

        assertEquals(ManufacturedType.GLASS.toString(), response.getResource().getName());
        assertEquals(10, response.getAmount());
    }
}
