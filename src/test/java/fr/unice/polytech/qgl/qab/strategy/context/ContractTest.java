package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.ContractItem;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.Contracts;
import static java.lang.Math.ceil;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 29/12/15.
 */
public class ContractTest {
    Contracts contracts;
    ContractItem contract;

    @Before
    public void defineContext() throws NegativeBudgetException {
        contracts = new Contracts();
        contract = new ContractItem(new ManufacturedResource(ManufacturedType.GLASS), 10);
    }

    @Test
    public void testInitialize() {
        assertEquals(10, contract.amount());
        assertEquals(ManufacturedType.GLASS.toString(), contract.resource().getName());
    }

    @Test (expected = NegativeBudgetException.class)
    public void testBadInicialize() throws NegativeBudgetException {
        contract = new ContractItem(new ManufacturedResource(ManufacturedType.GLASS), -10);
    }

    @Test
    public void testAmountNecessary() throws NegativeBudgetException{
        contracts.addContract("GLASS", 2);
        int wood = (int) (ceil(new ManufacturedResource(ManufacturedType.GLASS).getRecipe(2).get(PrimaryType.WOOD) * ContractItem.getMarginError()));
        int getAmount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(wood, getAmount);

        contracts.addContract("INGOT", 3);
        wood += (int) (ceil(new ManufacturedResource(ManufacturedType.INGOT).getRecipe(3).get(PrimaryType.WOOD) * ContractItem.getMarginError()));
        getAmount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(wood, getAmount);

        contracts.addContract("WOOD", 5);
        wood += 5;
        getAmount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(wood, getAmount);


    }

}