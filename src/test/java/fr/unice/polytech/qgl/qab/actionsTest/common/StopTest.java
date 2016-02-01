package fr.unice.polytech.qgl.qab.actionsTest.common;

import fr.unice.polytech.qgl.qab.actions.common.Stop;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 30/01/16.
 */
public class StopTest {
    Stop stop;

    @Before
    public void defineContext() {
        stop = new Stop();
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"stop\"}");
        assertTrue(stop.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"bla\": \"stop\"}");
        assertFalse(stop.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"stop\" }";
        assertTrue(stop.formatResponse().equals(response));
    }
}
