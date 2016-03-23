package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static java.lang.Math.ceil;

import static org.junit.Assert.*;

/**
 * @version 29/12/15.
 */
public class ContextTest {
    Context context;
    double marginError;
    @Before
    public void defineContext() throws NegativeBudgetException {
        context = new Context();
        marginError = ManufacturedResource.getMarginError();
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

    //TODO: FIX
    @Ignore
    public void testAccumulatedResources() throws NegativeBudgetException {
        context.addContract("FISH", 10);
        int amount = context.getAccumulatedAmountNecessary(new PrimaryResource(PrimaryType.FISH));
        assertEquals(10, amount);

        context.addContract("WOOD", 10);
        amount = context.getAccumulatedAmountNecessary(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(10, amount);

        context.addContract("GLASS", 10);
        amount = context.getAccumulatedAmountNecessary(new ManufacturedResource(ManufacturedType.GLASS));
        assertEquals(-1, amount); //Log with "error"

        //10 WOODS + 5*10*1.1 Woods = 10+55 WOODS
        //int wod = 10 + (int)ceil(5 * 10 * marginError);
        amount = context.getAccumulatedAmountNecessary(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(66, amount); //66 because ceil(65.000000001)


        //ceil(10*1.1) + ceil(5*10*1.1) + (20*5*1.1) = 11+55+110 =176
        context.addContract("INGOT", 20);
        amount = context.getAccumulatedAmountNecessary(new PrimaryResource(PrimaryType.WOOD));
        assertEquals(177, amount); //ceil(176.00000001)
    }

    //TODO: FIX
    @Ignore
    public void testLeather() throws  NegativeBudgetException{
        context.addContract("LEATHER", 1);
        int amount = context.getAccumulatedAmountNecessary(new PrimaryResource(PrimaryType.FUR));
        int recipe = new ManufacturedResource(ManufacturedType.LEATHER).getRecipe(1).get(PrimaryType.FUR);
        assertEquals((int)ceil(3 * marginError), amount);
        assertEquals(recipe, amount);
    }

    @Test
    public void testDecreaseAmout() throws NegativeBudgetException {

        context.addContract("WOOD", 10000);
        context.addCollectedResources(new PrimaryResource(PrimaryType.WOOD), 10);
        int collected = context.getCollectedResources().get(new PrimaryResource(PrimaryType.WOOD).getName());
        assertEquals(10, collected);

        int decrease = context.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.WOOD), 6);
        int newcollected = context.getCollectedResources().get(new PrimaryResource(PrimaryType.WOOD).getName());
        assertEquals(newcollected, collected-decrease);
        collected = newcollected;

        decrease = context.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.WOOD), 5);
        newcollected = context.getCollectedResources().get(new PrimaryResource(PrimaryType.WOOD).getName());
        assertEquals(newcollected, collected-decrease);
        assertNotEquals(collected-5, newcollected);
        collected = newcollected;

        context.decreaseAmountOfCollectedResources(new PrimaryResource(PrimaryType.FISH), 6);
    }

    @Test
    public void testgetIndex() throws NegativeBudgetException{
        Resource res0 = new PrimaryResource(PrimaryType.FISH);
        Resource res1 = new ManufacturedResource(ManufacturedType.GLASS);
        Resource res3 = new PrimaryResource(PrimaryType.WOOD);
        context.addContract("FISH", 3);
        context.addContract("GLASS", 5);

        int ind = context.getContractIndex(res0);
        assertEquals(0, ind);

        ind = context.getContractIndex(res1);
        assertEquals(1, ind);

        ind = context.getContractIndex(res3);
        assertEquals(-1, ind);

    }

    @Test
    public void testEnoughToTransform() throws NegativeBudgetException {
        context.addContract("GLASS", 7);
        assertFalse(context.enoughToTransform());

        int amount = 7;
        Map<PrimaryType, Integer> recipe = new ManufacturedResource(ManufacturedType.GLASS).getRecipe(7);
        for(Map.Entry<PrimaryType, Integer> getRecipe : recipe.entrySet()){
            PrimaryResource res = new PrimaryResource(getRecipe.getKey());
            context.addCollectedResources(res, getRecipe.getValue());
        }
        assertTrue(context.enoughToTransform());
        assertTrue(context.enoughToTransformAll());

        context.addContract("WOOD", 2);
        assertTrue(context.enoughToTransform());
        assertFalse(context.enoughToTransformAll());
        context.addCollectedResources(new PrimaryResource(PrimaryType.WOOD), 3);
        assertTrue(context.enoughToTransformAll());

        context.addContract("INGOT", 5);
        assertTrue(context.enoughToTransform());
        assertFalse(context.enoughToTransformAll()); //Not wood enough to transform glass AND ingot

    }

    //TODO: FIX
    @Ignore
    public void testEnoughToTransformAll() throws NegativeBudgetException{
        Resource fruits = new PrimaryResource(PrimaryType.FRUITS);
        Resource wood = new PrimaryResource(PrimaryType.WOOD);

        //ceil(4*1.1) = 5
        context.addContract("FRUITS", 4);
        context.addCollectedResources(fruits, 3);
        assertFalse(context.enoughToTransformAll());
        context.addCollectedResources(fruits, 2);
        assertTrue(context.enoughToTransformAll());


        //wood 14 + ceil(12*5*1.1) = 14 + 66 = 80
        context.addContract("WOOD", 14);
        context.addContract("INGOT", 12);
        context.addCollectedResources(wood, 40);
        assertFalse(context.enoughToTransformAll());
        context.addCollectedResources(wood, 40);
        assertTrue(context.enoughToTransformAll());

        context.addContract("PLANK", 12);
        context.addCollectedResources(wood, 2);
        assertFalse(context.enoughToTransformAll());
        context.addCollectedResources(wood, 2345678);
        assertTrue(context.enoughToTransformAll());

    }
}