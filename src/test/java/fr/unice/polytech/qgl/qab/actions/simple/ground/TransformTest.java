package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.ceil;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * version 14/03/2016.
 */
public class TransformTest {
    Context contex;
    Transform transform;
    Map<PrimaryType, Integer> recipe;

    @Before
    public void defineContext() throws NegativeBudgetException{
        contex = new Context();
        transform = new Transform(new HashMap<>(), contex);
        recipe = new ManufacturedResource(ManufacturedType.GLASS).getRecipe(1);
    }

    @Test
    public void responseFormat(){
        //add resources because can not create a transform if there is not the amount needed to transforme
        contex.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.WOOD), 100);
        contex.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.QUARTZ), 100);
        Transform trans = new Transform(recipe, contex);
        String transGlass = "{ \"action\": \"transform\", \"parameters\": { \"WOOD\": 5, \"QUARTZ\": 10 }}";
        trans.formatResponse().equalsIgnoreCase(transGlass);
    }

    @Test
    public void testValidTest() {
        int valueWood = recipe.get(PrimaryType.WOOD);
        int valueQuartz = recipe.get(PrimaryType.QUARTZ);
        JSONObject jsonObj = new JSONObject("{\"action\":\"transform\",\"parameters\":{\"WOOD\":\""+String.valueOf(valueWood)+"\",\"QUARTZ\":\""+String.valueOf(valueQuartz)+"\"}}");
        assertTrue(transform.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transform\", \"parameters\": { \"GLASS\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }

    @Test
    public void testWithoutActionNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"act\": \"transform\", \"parameters\": { \"WOOD\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }

    @Test
    public void testNotTransformNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transforme\", \"parameters\": { \"WOOD\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }
}