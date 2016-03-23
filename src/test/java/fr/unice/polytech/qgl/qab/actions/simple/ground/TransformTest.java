package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static java.lang.Math.ceil;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * version 14/03/2016.
 */
public class TransformTest {
    Transform transform;
    private static final double ERROR = 1.0; //1 + 10%

    @Before
    public void defineContext() {
        Map<PrimaryType, Integer> recipe = new ManufacturedResource(ManufacturedType.GLASS).getRecipe(1);
        transform = new Transform(recipe);
    }

    @Test
    public void testValideTest() {
        int valueWood = (int)(ceil(5 * ERROR));
        int valueQuartz = (int)(ceil(10 * ERROR));
        JSONObject jsonObj = new JSONObject("{\"action\":\"transform\",\"parameters\":{\"WOOD\":\""+String.valueOf(valueWood)+"\",\"QUARTZ\":\""+String.valueOf(valueQuartz)+"\"}}");
        assertTrue(transform.isValid(jsonObj));
    }

    @Test
    public void goodAnswerTest(){
        int valueWood = (int)(ceil(5 * ERROR));
        int valueQuartz = (int)(ceil(10 * ERROR));
        String response = "{\"action\":\"transform\",\"parameters\":{\"WOOD\":\""+String.valueOf(valueWood)+"\",\"QUARTZ\":\""+String.valueOf(valueQuartz)+"\"}}";
        assertTrue(response.equals(transform.formatResponse()));
    }

    @Test
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