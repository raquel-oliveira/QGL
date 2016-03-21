package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;
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

    @Before
    public void defineContext() throws NegativeBudgetException {
        contractItem = new ContractItem(new PrimaryResource(PrimaryType.FISH), 10);
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
}
