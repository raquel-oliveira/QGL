package fr.unice.polytech.qgl.qab.actionsTest.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.ground.Scout;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @version 31/01/16.
 */
public class ScoutTest {
    Scout scout;
    Direction dir;

    @Before
    public void defineContext() {
        dir = Direction.EAST;
        scout = new Scout(dir);
    }

    @Test
    public void testCreateGoodObject() throws NoSuchFieldException, IllegalAccessException {
        Field fieldDirection = Action.class.getDeclaredField("direction");
        fieldDirection.setAccessible(true);
        Direction value = (Direction) fieldDirection.get(scout);

        assertTrue(value.equals(dir));
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"scout\", \"parameters\": { \"direction\": \"E\" }}");
        assertTrue(scout.isValid(jsonObj));
    }
    @Test
    public void testValidJnjhson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"scout\", \"parameters\": { \"direction\": \""+dir.toString()+"\" }}");
        assertTrue(scout.isValid(jsonObj));
    }

    @Test
    public void testInvalideDirection() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"scout\", \"parameters\": { \"direction\": \"A\" }}");
        assertFalse(scout.isValid(jsonObj));
    }

    @Test
    public void testNotValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"notAction\": \"scout\", \"parameters\": { \"direction\": \"E\" }}");
        assertFalse(scout.isValid(jsonObj));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"action\": \"notscout\", \"parameters\": { \"direction\": \"E\" }}");
        assertFalse(scout.isValid(jsonObj));
    }

    @Test
    public void noParametersTest() {
        String response = "{ \"action\": \"scout\"}";
        JSONObject jsonObj = new JSONObject(response);
        assertFalse(scout.isValid(jsonObj));
        assertFalse(scout.formatResponse().equals(response));
    }

    @Test
    public void noDirectionParameterTest() {
        String response = "{ \"action\": \"scout\", \"parameters\": { \"bla\": \"E\" }}";
        JSONObject jsonObj = new JSONObject(response);
        assertFalse(scout.isValid(jsonObj));
        assertFalse(scout.formatResponse().equals(response));
    }

    @Test
    public void formatRequestTest() {
        String response = "{ \"action\": \"scout\", \"parameters\": { \"direction\": \"" + dir.toString() + "\" } }";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue(scout.isValid(jsonObj));
        assertTrue(scout.formatResponse().equals(response));
    }

    @Test
    public void getDirectionTest() {
        assertEquals(dir, scout.getDirection());
    }
}
