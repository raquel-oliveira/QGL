package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
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
    Transform transform;
    Map<PrimaryType, Integer> recipe;

    @Test
    public void defineContext() {
        recipe = new ManufacturedResource(ManufacturedType.GLASS).getRecipe(1);
    }

    @Ignore
    public void testValideTest() {
        int valueWood = recipe.get(PrimaryType.WOOD);
        int valueQuartz = recipe.get(PrimaryType.QUARTZ);
        JSONObject jsonObj = new JSONObject("{\"action\":\"transform\",\"parameters\":{\"WOOD\":\""+String.valueOf(valueWood)+"\",\"QUARTZ\":\""+String.valueOf(valueQuartz)+"\"}}");
        assertTrue(transform.isValid(jsonObj));
    }

    @Ignore
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transform\", \"parameters\": { \"GLASS\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }

    @Ignore
    public void testWithoutActionNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"act\": \"transform\", \"parameters\": { \"WOOD\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }

    @Ignore
    public void testNotTransformNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transforme\", \"parameters\": { \"WOOD\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }
}