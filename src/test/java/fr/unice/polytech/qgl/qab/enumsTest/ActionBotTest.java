package fr.unice.polytech.qgl.qab.enumsTest;

import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @version 4.9
 */
public class ActionBotTest {
    private ActionBot action;

    @Test
    public void testActionBotEcho() {
        action = ActionBot.ECHO;
        assertEquals(ActionBot.ECHO, action);
    }

    @Test
    public void testActionsBotLand() {
        action = ActionBot.LAND;
        assertEquals(ActionBot.LAND, action);
    }

    @Test
    public void testActionBotStop() {
        action = ActionBot.STOP;
        assertEquals(ActionBot.STOP, action);
    }

    @Test
    public void testActionBotScan() {
        action = ActionBot.SCAN;
        assertEquals(ActionBot.SCAN, action);
    }

    @Test
    public void testActionBotFly() {
        action = ActionBot.FLY;
        assertEquals(ActionBot.FLY, action);
    }

    @Test
    public void testToStringEcho() {
        action = ActionBot.ECHO;
        assertEquals(ActionBot.ECHO.toString(), action.toString());
    }

    @Test
    public void testToStringFly() {
        action = ActionBot.FLY;
        assertEquals(ActionBot.FLY.toString(), action.toString());
    }
    @Test
    public void testToStringScan() {
        action = ActionBot.SCAN;
        assertEquals(ActionBot.SCAN.toString(), action.toString());
    }
    @Test
    public void testToStringStop() {
        action = ActionBot.STOP;
        assertEquals(ActionBot.STOP.toString(), action.toString());
    }
    @Test
    public void testToStringLand() {
        action = ActionBot.LAND;
        assertEquals(ActionBot.LAND.toString(), action.toString());
    }

    @Test
    public void testFromStringEcho() {
        action = ActionBot.fromString("echo");
        assertTrue(action.isEquals(ActionBot.ECHO));
    }

    @Test
    public void testFromStringLand() {
        action = ActionBot.fromString("land");
        assertTrue(action.isEquals(ActionBot.LAND));
    }

    @Test
    public void testFromStringStop() {
        action = ActionBot.fromString("stop");
        assertTrue(action.isEquals(ActionBot.STOP));
    }

    @Test
    public void testFromStringScan() {
        action = ActionBot.fromString("scan");
        assertTrue(action.isEquals(ActionBot.SCAN));
    }

    @Test
    public void testFromStringFly() {
        action = ActionBot.fromString("fly");
        assertTrue(action.isEquals(ActionBot.FLY));
    }

    @Test(expected = NullPointerException.class)
    public void testEqualStringNull() {
        action = ActionBot.fromString("error");
        assertTrue(action.isEquals(null));
    }

    public void testFromStringNotExists() {
        action = ActionBot.fromString("error");
        assertEquals(null, action);
    }

    @Test(expected = NullPointerException.class)
    public void testToStringBeforeInitialize() {
        action.toString();
    }
}
