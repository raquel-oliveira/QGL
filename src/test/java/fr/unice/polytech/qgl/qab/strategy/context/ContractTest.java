package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.context.InsufficientException;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.ContractItem;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.Contracts;
import static java.lang.Math.ceil;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
        int wood =  new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int) (ceil(2 * ContractItem.getMarginError()))).get(PrimaryType.WOOD);
        int getAmount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(wood, getAmount);

        contracts.addContract("INGOT", 3);
        wood +=  new ManufacturedResource(ManufacturedType.INGOT).getRecipe((int) (ceil(3 * ContractItem.getMarginError()))).get(PrimaryType.WOOD);
        getAmount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(wood, getAmount);

        contracts.addContract("WOOD", 5);
        wood += 5;
        getAmount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(wood, getAmount);
    }

    @Test
    public void transformAll() throws NegativeBudgetException, InsufficientException{
        contracts.addContract("GLASS", 2);
        int wood = new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int) (ceil(2 * ContractItem.getMarginError()))).get(PrimaryType.WOOD);
        int quartz =  new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int) (ceil(2 * ContractItem.getMarginError()))).get(PrimaryType.QUARTZ);
        contracts.addCollectedResources(new PrimaryResource(PrimaryType.WOOD), wood);
        contracts.addCollectedResources(new PrimaryResource(PrimaryType.QUARTZ), quartz);
        assertTrue(contracts.enoughToTransformAll());

        contracts.addContract("INGOT", 3);
        contracts.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.WOOD), wood);
        contracts.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.QUARTZ), quartz);
        assertFalse(contracts.enoughToTransformAll());

        contracts.addCollectedResources(new PrimaryResource(PrimaryType.WOOD), 3);
        assertFalse(contracts.enoughToTransformAll());
    }

    @Test
    public void testAccumullatedResources() throws NegativeBudgetException {
        contracts.addContract("FISH", 10);
        int amount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.FISH));
        assertEquals(10, amount);

        contracts.addContract("WOOD", 10);
        int wood = 10;
        contracts.addContract("GLASS", 10);
        wood += new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int)(ceil(10 * ContractItem.getMarginError()))).get(PrimaryType.WOOD);
        amount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(wood, amount);

        contracts.addContract("INGOT", 20);
        wood += new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int)(ceil(20 * ContractItem.getMarginError()))).get(PrimaryType.WOOD);
        amount = contracts.getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(wood, amount);
    }

    @Test
    public void testDecreaseAmout() throws NegativeBudgetException, InsufficientException {
        contracts.addContract("WOOD", 10000);
        contracts.addCollectedResources(new PrimaryResource(PrimaryType.WOOD), 10);
        int collected = contracts.getCollectedResources().get(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(10, collected);

        int decrease = contracts.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.WOOD), 6);
        int newcollected = contracts.getCollectedResources().get(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(newcollected, collected-decrease);
        collected = newcollected;

        try{
            decrease = contracts.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.WOOD), 5);
            fail();
        }catch (InsufficientException e){

        }
    }

    @Test
    public void testgetIndex() throws NegativeBudgetException{
        Resource res0 = new PrimaryResource(PrimaryType.FISH);
        Resource res1 = new ManufacturedResource(ManufacturedType.GLASS);
        Resource res3 = new PrimaryResource(PrimaryType.WOOD);
        contracts.addContract("FISH", 3);
        contracts.addContract("GLASS", 5);

        int ind = contracts.getContractIndex(res0);
        assertEquals(0, ind);

        ind = contracts.getContractIndex(res1);
        assertEquals(1, ind);

        ind = contracts.getContractIndex(res3);
        assertEquals(-1, ind); //element not in the contract
    }
}