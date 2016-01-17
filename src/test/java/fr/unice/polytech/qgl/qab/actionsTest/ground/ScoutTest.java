package fr.unice.polytech.qgl.qab.actionsTest.ground;

import fr.unice.polytech.qgl.qab.actions.ground.Scout;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 17/01/16.
 */
public class ScoutTest {
    Scout scout;

    @Before
    public void defineContext() {
        scout = new Scout(Direction.EAST);
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"scout\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\" } }");
        assertTrue(scout.isValid(jsonObj));
    }

    @Test(expected = AssertionError.class)
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"acazerion\": \"scout\", \"azze\": { \"direction\": \"" + Direction.EAST + "\" } }");
        assertFalse(scout.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"scout\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\" } }";
        assertTrue(response.equals(scout.formatResponse()));
    }
}
