package fr.unice.polytech.qgl.qab.actions.simple.ground;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 31/01/16.
 */
public class ExploreTest {
    Explore explore;

    @Before
    public void defineContext() {
        explore = new Explore();
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"current\": \"explore\" }");
        assertTrue(explore.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{ \"current\": \"exploration\" }");
        assertFalse(explore.isValid(jsonObj));
        jsonObj = new JSONObject("{ \"act\": \"explore\" }");
        assertFalse(explore.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"current\": \"explore\" }";
        assertTrue(response.equals(explore.formatResponse()));
    }
}
