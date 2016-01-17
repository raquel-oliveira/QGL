package fr.unice.polytech.qgl.qab.actionsTest.ground;

import fr.unice.polytech.qgl.qab.actions.ground.Explore;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 17/01/16.
 */
public class ExploreTest {
    Explore e;

    @Before
    public void defineContext() {
        e = new Explore();
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"explore\" }");
        assertTrue(e.isValid(jsonObj));
    }

    @Test(expected = AssertionError.class)
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"actaddaion\": \"explore\" }");
        assertFalse(e.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"explore\" }";
        assertTrue(response.equals(e.formatResponse()));
    }
}
