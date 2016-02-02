package fr.unice.polytech.qgl.qab.context;

import static org.junit.Assert.*;

import fr.unice.polytech.qgl.qab.resources.*;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.junit.Test;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.strategy.context.ContractItem;

public class ContractTest {
	ContractItem c;
	
	@Test
	public void testAmount() throws NegativeBudgetException{
		Resource r = new PrimaryResource(PrimaryType.WOOD);
		c = new ContractItem(r,600);
		assertEquals(600,c.amount());
	}

	@Test
	public void testAccumulated() throws NegativeBudgetException{
		Resource r = new ManufacturedResource(ManufacturedType.GLASS);
		c = new ContractItem(r,200);
		c.collect(300);
		assertEquals(300,c.accumulated());
	}
	
	@Test
	public void Testresource() throws NegativeBudgetException{
		Resource r = new PrimaryResource(PrimaryType.WOOD);
		c = new ContractItem(r,600);
		assertEquals(r,c.resource());
	}
}
