package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @version 27/12/15.
 */
public class ComboFlyUntilTest {
    ComboFlyUntil combo;

    @Before
    public void defineContext() {
        combo = new ComboFlyUntil();
    }

    @Test
    public void testDefineComboFlyUntil() throws IndexOutOfBoundsComboAction {
        combo.defineComboFlyUntil(3);

        assertEquals(Fly.class, ((Fly)combo.get(0)).getClass());
        assertEquals(Fly.class, ((Fly)combo.get(1)).getClass());
        assertEquals(Fly.class, ((Fly)combo.get(2)).getClass());
    }

    @Test(expected = IndexOutOfBoundsComboAction.class)
    public void testGetBadIndex() throws IndexOutOfBoundsComboAction {
        combo.defineComboFlyUntil(3);
        assertEquals(Fly.class, ((Fly)combo.get(4)).getClass());
    }
}
