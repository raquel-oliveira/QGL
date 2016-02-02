package fr.unice.polytech.qgl.qab.actions.simple.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 17/01/2016.
 */
public class EchoTest {
    Echo echo;
    Direction dir;

    @Before
    public void defineContext() {
        dir = Direction.EAST;
        echo = new Echo(dir);
    }

    @Test
    public void testCreateGoodObject() throws NoSuchFieldException, IllegalAccessException {
        Field fieldDirection = Action.class.getDeclaredField("direction");
        fieldDirection.setAccessible(true);
        Direction value = (Direction) fieldDirection.get(echo);

        assertTrue(value.equals(dir));
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"echo\", \"parameters\": { \"direction\": \"E\" }}");
        assertTrue(echo.isValid(jsonObj));
    }
    @Test
    public void testValidJnjhson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"echo\", \"parameters\": { \"direction\": \""+dir.toString()+"\" }}");
        assertTrue(echo.isValid(jsonObj));
    }

    @Test
    public void testInvalideDirection() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"echo\", \"parameters\": { \"direction\": \"A\" }}");
        assertTrue(!echo.isValid(jsonObj));
    }

    @Test
    public void testNotValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"notAction\": \"echo\", \"parameters\": { \"direction\": \"E\" }}");
        assertTrue(!(echo.isValid(jsonObj)));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"action\": \"notEcho\", \"parameters\": { \"direction\": \"E\" }}");
        assertTrue(!(echo.isValid(jsonObj)));
    }

    @Test
    public void noParametersTest() {
        String response = "{ \"action\": \"echo\"}";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue(!(echo.isValid(jsonObj)));
        assertTrue(!(echo.formatResponse().equals(response)));
    }

    @Test
    public void noDirectionParameterTest() {
        String response = "{ \"action\": \"echo\", \"parameters\": { \"bla\": \"E\" }}";
        JSONObject jsonObj = new JSONObject(response);
        assertFalse(echo.isValid(jsonObj));
        assertFalse(echo.formatResponse().equals(response));
    }

    @Test
    public void formatRequestTest() {
        String response = "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + dir.toString() + "\" } }";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue((echo.isValid(jsonObj)));
        assertTrue((echo.formatResponse().equals(response)));
    }
}
