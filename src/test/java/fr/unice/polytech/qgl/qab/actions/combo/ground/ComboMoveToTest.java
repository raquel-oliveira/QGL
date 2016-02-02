package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 01/02/16.
 */
public class ComboMoveToTest {
    ComboMoveTo comboMoveTo;

    @Before
    public void defineContext() {
        comboMoveTo = new ComboMoveTo();
    }

    @Test
    public void testDefineAction() throws IndexOutOfBoundsComboAction {
        assertEquals(ComboMoveTo.class, comboMoveTo.getClass());

        comboMoveTo.defineActions(Direction.EAST, 2);
        assertEquals(2, comboMoveTo.getActions().size());
        assertEquals(Direction.EAST, comboMoveTo.get(0).getDirection());
    }
}
