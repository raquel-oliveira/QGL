package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @version 29/12/15.
 */
public class ContextTest {
    Context context;

    @Before
    public void defineContext() throws NegativeBudgetException {
        context = new Context();
    }

    @Test
    public void testReadContext() throws NegativeBudgetException {
        String contextString = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 10000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}";

        context.read(contextString);
        assertEquals(12, context.getMen());
        assertEquals(10000, context.getBudget());
        assertEquals(Direction.WEST, context.getFirstHead());
        assertEquals(Direction.WEST, context.getHeading());
    }

    @Test
    public void testCollectedResources() throws NegativeBudgetException {
        context.addContract("FISH", 10);

        context.addCollectedResources(new PrimaryResource(PrimaryType.FISH), 5);
        assertEquals(false, context.contractsAreComplete());

        context.addCollectedResources(new PrimaryResource(PrimaryType.FISH), 5);
        assertEquals(true, context.contractsAreComplete());
    }

    @Test
    public void testChangeContext() {
        context.updateToAerial();
        context.current().setLastAction(new Stop());
        assertEquals(Stop.class, context.current().getLastAction().getClass());

        context.updateToGround();
        context.current().setLastAction(new Fly());
        assertEquals(Fly.class, context.current().getLastAction().getClass());

        context.updateToAerial();
        assertEquals(Stop.class, context.current().getLastAction().getClass());

        context.updateToGround();
        assertEquals(Fly.class, context.current().getLastAction().getClass());
    }

    @Test
    public void testAcumullatedResources() throws NegativeBudgetException {
        context.addContract("FISH", 10);
        int amount = context.getAcumulatedAmountNecessary(new PrimaryResource(PrimaryType.FISH));
        assertEquals(10, amount);

        context.addContract("WOOD", 10);
        context.addContract("GLASS", 10);
        amount = context.getAcumulatedAmountNecessary(new ManufacturedResource(ManufacturedType.GLASS));
        assertEquals(0, amount);

        amount = context.getAcumulatedAmountNecessary(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(10, amount);

        context.addContract("INGOT", 20);
        amount = context.getAcumulatedAmountNecessary(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(10, amount);
    }

    @Test
    public void testDecreaseAmout() throws NegativeBudgetException {
        context.addContract("WOOD", 10000);
        context.addCollectedResources(new PrimaryResource(PrimaryType.WOOD), 10);
        int collected = context.getCollectedResources().get(new PrimaryResource(PrimaryType.WOOD).getName());
        assertEquals(10, collected);

        context.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.WOOD), 6);
        collected = context.getCollectedResources().get(new PrimaryResource(PrimaryType.WOOD).getName());
        assertEquals(4, collected);

        context.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.WOOD), 5);
        collected = context.getCollectedResources().get(new PrimaryResource(PrimaryType.WOOD).getName());
        assertNotEquals(-1, collected);
        assertNotEquals(4, collected);
        assertEquals(0, collected);

        context.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.FISH), 6);
    }
}