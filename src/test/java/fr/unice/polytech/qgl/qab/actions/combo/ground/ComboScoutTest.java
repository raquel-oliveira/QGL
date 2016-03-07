package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by huang on 07/03/16.
 */
public class ComboScoutTest {
    ComboScout comboScout;

    @Before
    public void defineComboScout(){
        comboScout = new ComboScout();
    }

    @Test
    public void testDefineAction() throws IndexOutOfBoundsComboAction {
        assertEquals(ComboScout.class, comboScout.getClass());

        comboScout.defineActions(Direction.EAST);
        assertTrue(comboScout.get(0).getDirection() == Direction.NORTH
                || comboScout.get(0).getDirection() == Direction.SOUTH);

        assertTrue(comboScout.get(1).getDirection() == Direction.SOUTH
                || comboScout.get(1).getDirection() == Direction.NORTH);

        assertEquals(Direction.EAST, comboScout.get(2).getDirection());

    }
}
