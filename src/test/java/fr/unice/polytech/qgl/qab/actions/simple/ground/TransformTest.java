package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * version 14/03/2016.
 */
public class TransformTest {
    private static final double marginError = 1.1;
    Transform transform;

    @Before
    public void defineContext() {
        Map<PrimaryType, Integer> recipe = new ManufacturedResource(ManufacturedType.GLASS).getRecipe(1);
        transform = new Transform(recipe);
    }

    @Test
    public void testValideTest() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transform\", \"parameters\": { \"WOOD\": 5, \"QUARTZ\": 10 }}");
        assertTrue(transform.isValid(jsonObj));
    }

    @Test
    public void goodAnswerTest(){
       // String response = "{\"action\":\"transform\",\"parameters\":{\"WOOD\":\"5\",\"QUARTZ\":\"10\"}}";
        //with 10%:
        String response = "{\"action\":\"transform\",\"parameters\":{\"WOOD\":\"6\",\"QUARTZ\":\"11\"}}";
        assertTrue(response.equals(transform.formatResponse()));
    }

    @Ignore
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transform\", \"parameters\": { \"GLASS\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }

    @Test
    public void testwithoutActionNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"act\": \"transform\", \"parameters\": { \"WOOD\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }

    @Test
    public void testNotTransformNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transforme\", \"parameters\": { \"WOOD\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }
}
