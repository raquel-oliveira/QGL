package fr.unice.polytech.qgl.qab.actionsTest.aerial;

import fr.unice.polytech.qgl.qab.actions.aerial.Scan;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @version 27/12/2015.
 */
public class ScanTest {
    Scan scan;

    @Before
    public void defineContext() {
        scan = new Scan();
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"scan\"}");
        assertTrue(scan.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"bla\": \"scan\"}");
        assertTrue(!(scan.isValid(jsonObj)));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"scan\" }";
        assertTrue(scan.formatResponse().equals(response));
    }
}
