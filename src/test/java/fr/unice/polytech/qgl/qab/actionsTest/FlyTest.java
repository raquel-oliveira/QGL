package fr.unice.polytech.qgl.qab.actionsTest;

import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Raquel on 27/12/2015.
 */
public class FlyTest {
    Fly fly;

    @Before
    public void defineContext() {
        fly = new Fly();
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"fly\"}");
        assertTrue(fly.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"bla\": \"fly\"}");
        assertTrue(!(fly.isValid(jsonObj)));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"fly\"}";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue(!(fly.formatResponse().equals(response)));
    }
}
