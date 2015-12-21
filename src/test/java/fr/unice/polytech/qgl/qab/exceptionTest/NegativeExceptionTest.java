package fr.unice.polytech.qgl.qab.exceptionTest;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.strategy.context.Budget;
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
