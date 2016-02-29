package fr.unice.polytech.qgl.qab.context;

import static org.junit.Assert.*;

import fr.unice.polytech.qgl.qab.resources.*;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;
import org.junit.Test;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;

public class ContractTest {
	ContractItem c;

	@Test
	public void testAmount() throws NegativeBudgetException{
		Resource r = new PrimaryResource(PrimaryType.WOOD);
		c = new ContractItem(r,600);
		assertEquals(600,c.amount());
	}

	@Test
	public void Testresource() throws NegativeBudgetException{
		Resource r = new PrimaryResource(PrimaryType.WOOD);
		c = new ContractItem(r,600);
		assertEquals(r,c.resource());
	}
}