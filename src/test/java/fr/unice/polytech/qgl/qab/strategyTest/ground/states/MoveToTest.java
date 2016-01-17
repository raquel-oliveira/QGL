package fr.unice.polytech.qgl.qab.strategyTest.ground.states;

import fr.unice.polytech.qgl.qab.actions.ground.MoveTo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 31/12/15.
 */
public class MoveToTest {
    MoveTo moveTo;

    @Ignore @Before
    public void defineContext() {
        moveTo = new MoveTo(Direction.EAST);
    }

    @Ignore @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"move_to\"}");
        assertTrue(moveTo.isValid(jsonObj));
    }

    @Ignore @Test (expected = AssertionError.class)
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"bla\": \"move_to\"}");
        assertFalse(moveTo.isValid(jsonObj));
    }

    @Ignore @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"move_to\"}";
        assertFalse(moveTo.formatResponse().equals(response));
    }
}
