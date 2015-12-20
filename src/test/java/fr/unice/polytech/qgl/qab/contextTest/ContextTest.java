package fr.unice.polytech.qgl.qab.contextTest;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.unice.polytech.qgl.qab.exception.InitializeException;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

public class ContextTest {
	Context c;
	
	@Test
	public void TestgetHeading() throws InitializeException{
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
	public void TestgetBudget() throws InitializeException{
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
