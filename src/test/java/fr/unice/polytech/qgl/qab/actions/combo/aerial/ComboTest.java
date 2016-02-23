package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @version 27/12/15.
 */
public class ComboTest {
    Combo combo;

    @Test
    public void testInitialize() {
        combo = new ComboEchos();
        assertTrue(combo.getActions().equals(new ArrayList<>()));
        assertEquals(combo.getClass(), ComboEchos.class);

        combo = new ComboFlyEcho();
        assertTrue(combo.getActions().equals(new ArrayList<>()));
        assertEquals(combo.getClass(), ComboFlyEcho.class);

        combo = new ComboFlyScan();
        assertTrue(combo.getActions().equals(new ArrayList<>()));
        assertEquals(combo.getClass(), ComboFlyScan.class);

        combo = new ComboFlyUntil();
        assertTrue(combo.getActions().equals(new ArrayList<>()));
        assertEquals(combo.getClass(), ComboFlyUntil.class);

        combo = new ComboFlyUntil();
        assertTrue(combo.getActions().equals(new ArrayList<>()));
        assertEquals(combo.getClass(), ComboFlyUntil.class);

        combo = new ComboReturn();
        assertTrue(combo.getActions().equals(new ArrayList<>()));
        assertEquals(combo.getClass(), ComboReturn.class);
    }

    @Test(expected = IndexOutOfBoundsComboAction.class)
    public void testIsEmpty() throws IndexOutOfBoundsComboAction {
        combo = new ComboEchos();
        combo.get(0);
    }

    @Test(expected = IndexOutOfBoundsComboAction.class)
    public void testBadIndex() throws IndexOutOfBoundsComboAction {
        ComboEchos comboEchos = new ComboEchos();
        comboEchos.defineActions(Direction.EAST);
        comboEchos.get(-5);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerException() throws IndexOutOfBoundsComboAction {
        ComboEchos comboEchos = new ComboEchos();
        comboEchos.defineActions(Direction.EAST);
        combo.get(5);
    }
}
