package fr.unice.polytech.qgl.qab.context;

import static org.junit.Assert.*;

import fr.unice.polytech.qgl.qab.strategy.context.utils.Budget;
import org.junit.Test;

import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;

public class BudgetTest {
	private Budget b;
	
	@Test
	public void TestInitial() throws NegativeBudgetException {
		b = new Budget(500);
		assertEquals(500,b.initial());
	}
	
	
	@Test
	public void Testremaining() throws NegativeBudgetException {
		b = new Budget(500);
		b.spend(100);
		assertEquals(400, b.remaining());
	}
}
