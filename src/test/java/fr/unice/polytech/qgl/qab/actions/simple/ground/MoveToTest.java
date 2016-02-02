package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 17/01/16.
 */
public class MoveToTest {
    MoveTo move;

    @Before
    public void defineContext() {
        move = new MoveTo(Direction.EAST);
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"move_to\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\" } }");
        assertTrue(move.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"aon\": \"move_to\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\" } }");
        assertFalse(move.isValid(jsonObj));
        jsonObj = new JSONObject("{ \"action\": \"mov\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\" } }");
        assertFalse(move.isValid(jsonObj));
        jsonObj = new JSONObject("{ \"action\": \"move_to\", \"parameters\": { \"dir\": \"" + Direction.EAST + "\" } }");
        assertFalse(move.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"move_to\", \"parameters\": { \"direction\": \"" + Direction.EAST + "\" } }";
        assertTrue(response.equals(move.formatResponse()));
    }
}
