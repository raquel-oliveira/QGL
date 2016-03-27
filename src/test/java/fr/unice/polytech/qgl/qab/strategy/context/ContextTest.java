package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.ContractItem;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import static java.lang.Math.ceil;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.Object;

import static org.junit.Assert.*;

/**
 * @version 29/12/15.
 */
public class ContextTest {
    Context context;
    private static final double marginError = 1.5;

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
        context.getContracts().addContract("FISH", 10);

        context.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.FISH), 5);
        assertEquals(false, context.getContracts().contractsAreComplete());

        context.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.FISH), 5);
        assertEquals(true, context.getContracts().contractsAreComplete());
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
    public void testLeather() throws  NegativeBudgetException{
        context.getContracts().addContract("LEATHER", 1);
        int amount = context.getContracts().getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.FUR));
        int recipe = new ManufacturedResource(ManufacturedType.LEATHER).getRecipe((int)(ceil(1 * ContractItem.getMarginError()))).get(PrimaryType.FUR);
        assertEquals(recipe, amount);
    }
}