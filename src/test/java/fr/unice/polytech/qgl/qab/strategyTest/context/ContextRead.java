package fr.unice.polytech.qgl.qab.strategyTest.context;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 29/12/15.
 */
public class ContextRead {
    Context context;

    @Before
    public void defineContext() throws NegativeBudgetException {
        context = new Context();
    }

    @Test
    public void testReadContext() throws NegativeBudgetException {
        String contextString = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 10000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}";

        context.read(contextString);
        assertEquals(12, context.getMen());
        assertEquals(10000, context.getBudget());
        assertEquals(Direction.WEST, context.getFirstHead());
        assertEquals(Direction.WEST, context.getHeading());
    }
}
