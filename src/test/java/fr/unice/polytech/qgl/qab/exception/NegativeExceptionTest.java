package fr.unice.polytech.qgl.qab.exception;

import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.Budget;
import org.junit.Test;

/**
 * @version 20/12/15.
 */
public class NegativeExceptionTest {

    Budget b;

    @Test(expected = NegativeBudgetException.class)
    public void budgetInitialNegative() throws NegativeBudgetException {
        b = new Budget(-10);
    }

    @Test(expected = NegativeBudgetException.class)
    public void budgetSpendNegative() throws NegativeBudgetException {
        b = new Budget(10);
        b.spend(50);
    }
}
