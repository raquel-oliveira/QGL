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
    public void testActionBotGlimpse() {
        action = ActionBot.GLIMPSE;
        assertEquals(ActionBot.GLIMPSE, action);
    }

    @Test
    public void testActionBotMoveTo() {
        action = ActionBot.MOVE_TO;
        assertEquals(ActionBot.MOVE_TO, action);
    }

    @Test
    public void testActionBotExplore() {
        action = ActionBot.EXPLORE;
        assertEquals(ActionBot.EXPLORE, action);
    }

    @Test
    public void testActionBotScout() {
        action = ActionBot.SCOUT;
        assertEquals(ActionBot.SCOUT, action);
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
    public void testToStringGlimpse() {
        action = ActionBot.GLIMPSE;
        assertEquals(ActionBot.GLIMPSE.toString(), action.toString());
    }

    @Test
    public void testToStringMoveTo() {
        action = ActionBot.MOVE_TO;
        assertEquals(ActionBot.MOVE_TO.toString(), action.toString());
    }

    @Test
    public void testToStringExplore() {
        action = ActionBot.EXPLORE;
        assertEquals(ActionBot.EXPLORE.toString(), action.toString());
    }

    @Test
    public void testToStringScout() {
        action = ActionBot.SCOUT;
        assertEquals(ActionBot.SCOUT.toString(), action.toString());
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

    @Test
    public void testFromStringGlimpse() {
        action = ActionBot.fromString("glimpse");
        assertTrue(action.equals(ActionBot.GLIMPSE));
    }

    @Test
    public void testFromStringMoveTo() {
        action = ActionBot.fromString("move_to");
        assertTrue(action.equals(ActionBot.MOVE_TO));
    }

    @Test
    public void testFromStringExplore() {
        action = ActionBot.fromString("explore");
        assertTrue(action.equals(ActionBot.EXPLORE));
    }

    @Test
    public void testFromStringScout() {
        action = ActionBot.fromString("scout");
        assertTrue(action.equals(ActionBot.SCOUT));
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
