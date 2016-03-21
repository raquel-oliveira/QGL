package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * version 14/03/2016.
 */
public class TransformResponseTest {
    TransformResponse response;
    Context context;

    @Before
    public void defineContext() throws NegativeBudgetException {
        response = new TransformResponse();
        context = new Context();
    }

    @Test
    public void testInitialize() {
        assertEquals(null, response.getResource());
        assertEquals(0, response.getAmount());

        response.addData(new ManufacturedResource(ManufacturedType.GLASS), 10, context);

        assertEquals(ManufacturedType.GLASS.toString(), response.getResource().getName());
        assertEquals(10, response.getAmount());
    }
}
