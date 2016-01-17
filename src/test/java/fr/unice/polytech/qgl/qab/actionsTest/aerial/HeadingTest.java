package fr.unice.polytech.qgl.qab.actionsTest.aerial;

import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Raquel on 27/12/2015.
 */
public class HeadingTest {
    Heading heading;
    Direction dir;

    @Before
    public void defineContext() {
        dir = Direction.EAST;
        heading = new Heading(dir);
    }

    @Test
    public void testCreateGoodObject() throws NoSuchFieldException, IllegalAccessException {
        Field fieldDirection = Heading.class.getDeclaredField("direction");
        fieldDirection.setAccessible(true);
        Direction value = (Direction) fieldDirection.get(heading);

        assertTrue(value.equals(dir));
    }

    @Test
    public void testValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"heading\", \"parameters\": { \"direction\": \"E\" }}");
        assertTrue(heading.isValid(jsonObj));
    }
  @Test
  public void testValidJnjhson() {
      JSONObject jsonObj = new JSONObject("{ \"action\": \"heading\", \"parameters\": { \"direction\": \""+dir.toString()+"\" }}");
      assertTrue(heading.isValid(jsonObj));
  }

    @Test
    public void testInvalideDirection() {
        JSONObject jsonObj = new JSONObject("{ \"action\": \"heading\", \"parameters\": { \"direction\": \"A\" }}");
        assertTrue(!heading.isValid(jsonObj));
    }

    @Test
    public void testNotValidJson() {
        JSONObject jsonObj = new JSONObject("{ \"notAction\": \"heading\", \"parameters\": { \"direction\": \"E\" }}");
        assertTrue(!(heading.isValid(jsonObj)));
    }

    @Test
    public void testNotValidActionJson() {
        JSONObject jsonObj = new JSONObject("{\"action\": \"notHeading\", \"parameters\": { \"direction\": \"E\" }}");
        assertTrue(!(heading.isValid(jsonObj)));
    }

    @Test
    public void noParametersTest() {
        String response = "{ \"action\": \"heading\"}";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue(!(heading.isValid(jsonObj)));
        assertTrue(!(heading.formatResponse().equals(response)));
    }

    @Test
    public void noDirectionParameterTest() {
        String response = "{ \"action\": \"heading\", \"parameters\": { \"bla\": \"E\" }}";
        JSONObject jsonObj = new JSONObject(response);
        assertFalse(heading.isValid(jsonObj));
        assertFalse(heading.formatResponse().equals(response));
    }

    @Test
    public void formatRequestTest() {
        String response = "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"" + dir.toString() + "\" } }";
        JSONObject jsonObj = new JSONObject(response);
        assertTrue((heading.isValid(jsonObj)));
        assertTrue((heading.formatResponse().equals(response)));
    }
}
