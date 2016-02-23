package fr.unice.polytech.qgl.qab.actions.simple.aerial;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 27/12/2015.
 */
public class FlyTest {
    Fly fly;

    @Before
    public void defineContext() {
        fly = new Fly();
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"current\": \"fly\"}");
        assertTrue(fly.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"bla\": \"fly\"}");
        assertFalse(fly.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"current\": \"fly\" }";
        assertTrue(fly.formatResponse().equals(response));
    }
}
