package fr.unice.polytech.qgl.qab.actionsTest;

import fr.unice.polytech.qgl.qab.actions.common.Land;
import fr.unice.polytech.qgl.qab.strategy.Strategy;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertTrue;

/**
 * @version 21/12/15.
 */
public class LandTest {
    Land land;

    @Before
    public void defineContext() {
        land = new Land("aaglk14sd12-&1", 1);
    }

    @Test
    public void testCreateGoodObject() throws NoSuchFieldException, IllegalAccessException {
        Field fieldID = Land.class.getDeclaredField("id");
        fieldID.setAccessible(true);
        String value = (String) fieldID.get(land);

        assertTrue(value.equals("aaglk14sd12-&1"));

        Field fieldPeople = Land.class.getDeclaredField("people");
        fieldPeople.setAccessible(true);
        int people = (int) fieldPeople.get(land);

        assertTrue(people == 1);
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"land\", \"parameters\": { \"creek\": \"id\", \"people\": 42 }}");
        assertTrue(land.isValid(jsonObj));
    }

    @Test
    public void testNotValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"stop\", \"parameters\": { \"creek\": \"id\", \"people\": 42 }}");
        assertTrue(!(land.isValid(jsonObj)));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"bla\": \"land\", \"parameters\": { \"creek\": \"id\", \"people\": 42 }}");
        assertTrue(!(land.isValid(jsonObj)));
    }

    @Test
    public void noParametersTest() {
        String response = "{ \"action\": \"land\"}";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue(!(land.isValid(jsonObj)));
        assertTrue(!(land.formatResponse().equals(response)));
    }

    @Test
    public void noCreekTest() {
        String response = "{ \"action\": \"land\", \"parameters\": { \"people\": 1 }}";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue(!(land.isValid(jsonObj)));
        assertTrue(!(land.formatResponse().equals(response)));
    }

    @Test
    public void noPeopleTest() {
        String response = "{ \"action\": \"land\", \"parameters\": { \"creek\": \"aaglk14sd12-&1\"}}";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue(!(land.isValid(jsonObj)));
        assertTrue(!(land.formatResponse().equals(response)));
    }

    @Test
    public void formatResponseTest() {
        String response = "{ \"action\": \"land\", \"parameters\": { \"creek\": \"aaglk14sd12-&1\", \"people\": 1 }}";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue(!(land.isValid(jsonObj)));
        assertTrue(!(land.formatResponse().equals(response)));
    }

}
