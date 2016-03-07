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
public class ComboGlimpseTest {
    ComboGlimpse comboGlimpse;

    @Before
    public void defineComboGlimpse(){
        comboGlimpse = new ComboGlimpse();
    }

    @Test
    public void testDefineAction() throws IndexOutOfBoundsComboAction {
        assertEquals(ComboGlimpse.class, comboGlimpse.getClass());

        comboGlimpse.defineActions(Direction.NORTH);
        assertEquals(Direction.SOUTH, comboGlimpse.get(0).getDirection());
        assertTrue(comboGlimpse.get(1).getDirection() == Direction.EAST
                || comboGlimpse.get(1).getDirection() == Direction.WEST);
        assertTrue(comboGlimpse.get(2).getDirection() == Direction.WEST
                || comboGlimpse.get(2).getDirection() == Direction.EAST);
    }
}
