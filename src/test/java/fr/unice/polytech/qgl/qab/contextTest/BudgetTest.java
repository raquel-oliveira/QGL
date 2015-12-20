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
		b = Budget.getInstance(500, 500);
		assertEquals(500,b.initial());
	}
	
	
	@Test
	public void Testremaining() throws InitializeException{
		b = Budget.getInstance(500, 400);
		assertEquals(400,b.remaining());
		
	}
	
	@Test
	public void TestSpend() throws InitializeException, NegativeException{
		b = Budget.getInstance(500, 100);
		b.spend(50);
		assertEquals(50,b.remaining());
	}
	
	

}
