package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 31/01/16.
 */
public class GlimpseTest {
    Glimpse glimpse;

    @Before
    public void defineContext() {
        glimpse = new Glimpse(Direction.NORTH, 2);
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"glimpse\", \"parameters\": { \"direction\": \"N\", \"range\": 2 } }");
        assertTrue(glimpse.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"act\": \"glimpse\", \"parameters\": { \"direction\": \"N\", \"range\": 2 } }");
        assertFalse(glimpse.isValid(jsonObj));
        jsonObj = new JSONObject("{ \"action\": \"glim\", \"parameters\": { \"direction\": \"N\", \"range\": 2 } }");
        assertFalse(glimpse.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"glimpse\", \"parameters\": { \"direction\": \"N\", \"range\": 2 } }";
        assertTrue(response.equals(glimpse.formatResponse()));
    }

    @Test
    public void getDirectionTest() {
        assertEquals(Direction.NORTH, glimpse.getDirection());
    }
}
