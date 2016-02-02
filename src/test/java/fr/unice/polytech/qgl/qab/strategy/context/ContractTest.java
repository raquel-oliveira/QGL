package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 29/12/15.
 */
public class ContractTest {
    ContractItem contract;

    @Before
    public void defineContext() throws NegativeBudgetException {
        contract = new ContractItem(new Resource("TEST"), 10);
    }

    @Test
    public void testInicialize() {
        assertEquals(10, contract.amount());
        assertEquals("TEST", contract.resource().getName());
    }

    @Test (expected = NegativeBudgetException.class)
    public void testBadInicialize() throws NegativeBudgetException {
        contract = new ContractItem(new Resource("TEST"), -10);
    }

    @Test
    public void testCollect() throws NegativeBudgetException {
        contract.collect(10);
        assertEquals(10, contract.accumulated());

        contract.collect(10);
        assertEquals(20, contract.accumulated());
    }

    @Test (expected = NegativeBudgetException.class)
    public void testBadCollect() throws NegativeBudgetException {
        contract.collect(-10);
    }
}
