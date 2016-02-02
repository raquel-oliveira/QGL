package fr.unice.polytech.qgl.qab.context;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.strategy.context.ContractItem;

public class ContractTest {
	ContractItem c;
	
	@Test
	public void Testamount() throws NegativeBudgetException{
		Resource r = new Resource("WOOD");
		c = new ContractItem(r,600);
		assertEquals(600,c.amount());
	}

	@Test
	public void Testaccumulated() throws NegativeBudgetException{
		Resource r = new Resource("GLASS");
		c = new ContractItem(r,200);
		c.collect(300);
		assertEquals(300,c.accumulated());
	}
	
	@Test
	public void Testresource() throws NegativeBudgetException{
		Resource r = new Resource("WOOD");
		c = new ContractItem(r,600);
		assertEquals(r,c.resource());
	}
}
