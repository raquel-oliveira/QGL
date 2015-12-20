package fr.unice.polytech.qgl.qab.contextTest;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.unice.polytech.qgl.qab.exception.InitializeException;
import fr.unice.polytech.qgl.qab.exception.NegativeException;
import fr.unice.polytech.qgl.qab.strategy.context.Budget;

public class BudgetTest {
	private Budget b;
	
	@Test
	public void TestInitial() throws InitializeException{
		b = new Budget(500);
		assertEquals(500,b.initial());
	}
	
	
	@Test
	public void Testremaining() throws InitializeException, NegativeException {
		b = new Budget(500);
		b.spend(100);
		assertEquals(400, b.remaining());
		
	}
}
