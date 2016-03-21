package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.ContractItem;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 07/03/16.
 */
public class ContractItemTest {
    ContractItem contractItem;
    ContractItem itemManufactured;
    Context context;

    @Before
    public void defineContext() throws NegativeBudgetException {
        context = new Context();
        contractItem = new ContractItem(new PrimaryResource(PrimaryType.FISH), 10);
        itemManufactured = new ContractItem(new ManufacturedResource(ManufacturedType.RUM), 11);
        //context.addContract("RUM", 11);
        context.getContracts().addContract(itemManufactured.resource().getName(), 11);
    }

    @Test
    public void testItem() {
        assertEquals(10, contractItem.amount());
        assertEquals(PrimaryResource.class, contractItem.resource().getClass());
        HashMap<String, Integer> item = new HashMap<>();
        assertFalse(contractItem.isComplete(item));
        item.put("FISH", 10);
        assertTrue(contractItem.isComplete(item));
    }

    @Test
    public void testCompleteManufactured() {
        assertEquals(11, itemManufactured.amount());
        assertEquals(ManufacturedResource.class, itemManufactured.resource().getClass());

        assertFalse(itemManufactured.isComplete(context.getContracts().getCollectedResources()));

        context.getContracts().addCollectedResources(new ManufacturedResource(ManufacturedType.RUM), 3);
        assertFalse(itemManufactured.isComplete(context.getContracts().getCollectedResources()));
        int collectedRum = context.getContracts().getCollectedResources().get(new ManufacturedResource(ManufacturedType.RUM).getName());
        assertEquals(3, collectedRum);

        context.getContracts().addCollectedResources(new ManufacturedResource(ManufacturedType.RUM), 3);
        collectedRum = context.getContracts().getCollectedResources().get(new ManufacturedResource(ManufacturedType.RUM).getName());
        assertEquals(6, collectedRum);

        context.getContracts().addCollectedResources(new ManufacturedResource(ManufacturedType.RUM), 10);
        collectedRum = context.getContracts().getCollectedResources().get(new ManufacturedResource(ManufacturedType.RUM).getName());
        assertEquals(16, collectedRum);
        assertTrue(itemManufactured.isComplete(context.getContracts().getCollectedResources()));
    }
}
