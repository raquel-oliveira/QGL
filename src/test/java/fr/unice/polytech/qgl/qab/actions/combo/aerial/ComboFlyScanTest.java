package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @version 27/12/15.
 */
public class ComboFlyScanTest {
    ComboFlyScan combo;

    @Before
    public void defineContext() {
        combo = new ComboFlyScan();
    }

    @Test
    public void testDefine() throws IndexOutOfBoundsComboAction {
        combo.defineActions();
        assertEquals(Fly.class, ((Fly)combo.get(0)).getClass());
        assertEquals(Scan.class, ((Scan)combo.get(1)).getClass());
    }
}
