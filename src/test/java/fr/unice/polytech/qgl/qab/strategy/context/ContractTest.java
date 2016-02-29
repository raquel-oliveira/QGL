package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;
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
        contract = new ContractItem(new ManufacturedResource(ManufacturedType.GLASS), 10);
    }

    @Test
    public void testInicialize() {
        assertEquals(10, contract.amount());
        assertEquals(ManufacturedType.GLASS.toString(), contract.resource().getName());
    }

    @Test (expected = NegativeBudgetException.class)
    public void testBadInicialize() throws NegativeBudgetException {
        contract = new ContractItem(new ManufacturedResource(ManufacturedType.GLASS), -10);
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