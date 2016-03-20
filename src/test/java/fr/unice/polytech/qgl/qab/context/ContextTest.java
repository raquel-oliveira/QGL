package fr.unice.polytech.qgl.qab.context;

import static org.junit.Assert.*;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.junit.Before;
import org.junit.Ignore;

import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Test;

public class ContextTest {
	Context c;

	@Before
	public void defineContext() throws NegativeBudgetException {
		c = new Context();
	}
	
	@Test
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
	
	@Test
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
		assertEquals(1000,c.getBudget());
	}
}
