package fr.unice.polytech.qgl.qab.context;

import static org.junit.Assert.*;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import org.junit.Ignore;

import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

public class ContextTest {
	Context c;
	
	@Ignore
	public void TestgetHeading() throws NegativeBudgetException {
		String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
		c.read(context);
		assertEquals(Direction.WEST,c.getHeading());
	}
	
	@Ignore
	public void TestgetBudget() throws NegativeBudgetException {
		String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
		c.read(context);
		assertEquals("1000",c.getBudget());
	}
}
