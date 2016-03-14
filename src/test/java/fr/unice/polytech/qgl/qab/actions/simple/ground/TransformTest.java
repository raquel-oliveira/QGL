package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * version 14/03/2016.
 */
public class TransformTest {
    Transform transform;

    @Before
    public void defineContext() {
        Map<PrimaryType, Integer> recipe = new ManufacturedResource(ManufacturedType.GLASS).getRecipe(1);
        transform = new Transform(recipe);
    }

    @Test
    public void formatResponseTest() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transform\", \"parameters\": { \"WOOD\": 6, \"QUARTZ\": 11 }}\n");
        assertTrue(transform.isValid(jsonObj));
    }

    @Ignore
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"transform\", \"parameters\": { \"GLASS\": 6, \"QUARTZ\": 11 }}\n");
        assertFalse(transform.isValid(jsonObj));
    }
}
