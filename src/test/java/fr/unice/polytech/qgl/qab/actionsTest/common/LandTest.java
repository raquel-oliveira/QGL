package fr.unice.polytech.qgl.qab.actionsTest.common;

import fr.unice.polytech.qgl.qab.actions.common.Land;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 30/01/16.
 */
public class LandTest {
    Land land;

    static final String ID = "123ADB";
    static final int PEOPLE = 10;

    @Before
    public void defineContext() {
        land = new Land(ID, PEOPLE);
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"land\", \"parameters\": { \"creek\": \""+ID+"\", \"people\": "+PEOPLE+" }}");
        assertTrue(land.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"bla\": \"land\"}");
        assertFalse(land.isValid(jsonObj));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"land\", \"parameters\": { \"creek\": \""+ID+"\", \"people\": "+PEOPLE+" }}";
        assertTrue(land.formatResponse().equals(response));
    }
}
