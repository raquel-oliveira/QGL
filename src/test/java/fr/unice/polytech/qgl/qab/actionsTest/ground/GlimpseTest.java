package fr.unice.polytech.qgl.qab.actionsTest.ground;

import fr.unice.polytech.qgl.qab.actions.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.ground.Glimpse;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 17/01/16.
 */
public class GlimpseTest {
    Glimpse g;

    @Before
    public void defineContext() {
        g = new Glimpse(Direction.EAST, 5);
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"glimpse\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\", \"range\": "+5+" } }");
        assertTrue(g.isValid(jsonObj));
    }

    @Test(expected = AssertionError.class)
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"aon\": \"glimpse\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\", \"range\": "+5+" } }");
        assertFalse(g.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"glimpse\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\", \"range\": "+5+" } }";
        assertTrue(response.equals(g.formatResponse()));
    }
}
