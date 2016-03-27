package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 01/02/16.
 */
public class ComboReturnGroundTest {
    ComboReturnGround comboReturnGround;

    @Before
    public void defineContext() {
        comboReturnGround = new ComboReturnGround();
    }

    @Test
    public void testDefineAction() throws IndexOutOfBoundsComboAction {
        assertEquals(ComboReturnGround.class, comboReturnGround.getClass());

        comboReturnGround.defineActions(Direction.EAST, Direction.SOUTH);
        assertEquals(2, comboReturnGround.getActions().size());
        assertEquals(Direction.SOUTH, comboReturnGround.get(0).getDirection());
        assertEquals(Direction.WEST, comboReturnGround.get(1).getDirection());

        defineContext();

        comboReturnGround.defineActions(Direction.WEST, Direction.SOUTH);
        assertEquals(2, comboReturnGround.getActions().size());
        assertEquals(Direction.SOUTH, comboReturnGround.get(0).getDirection());
        assertEquals(Direction.EAST, comboReturnGround.get(1).getDirection());

        defineContext();

        comboReturnGround.defineActions(Direction.WEST, Direction.NORTH);
        assertEquals(2, comboReturnGround.getActions().size());
        assertEquals(Direction.NORTH, comboReturnGround.get(0).getDirection());
        assertEquals(Direction.EAST, comboReturnGround.get(1).getDirection());

        defineContext();

        comboReturnGround.defineActions(Direction.EAST, Direction.NORTH);
        assertEquals(2, comboReturnGround.getActions().size());
        assertEquals(Direction.NORTH, comboReturnGround.get(0).getDirection());
        assertEquals(Direction.WEST, comboReturnGround.get(1).getDirection());

        defineContext();

        comboReturnGround.defineActions(Direction.SOUTH, Direction.EAST);
        assertEquals(2, comboReturnGround.getActions().size());
        assertEquals(Direction.EAST, comboReturnGround.get(0).getDirection());
        assertEquals(Direction.NORTH, comboReturnGround.get(1).getDirection());

        defineContext();

        comboReturnGround.defineActions(Direction.SOUTH, Direction.WEST);
        assertEquals(2, comboReturnGround.getActions().size());
        assertEquals(Direction.WEST, comboReturnGround.get(0).getDirection());
        assertEquals(Direction.NORTH, comboReturnGround.get(1).getDirection());

        defineContext();

        comboReturnGround.defineActions(Direction.NORTH, Direction.EAST);
        assertEquals(2, comboReturnGround.getActions().size());
        assertEquals(Direction.EAST, comboReturnGround.get(0).getDirection());
        assertEquals(Direction.SOUTH, comboReturnGround.get(1).getDirection());

        defineContext();

        comboReturnGround.defineActions(Direction.NORTH, Direction.WEST);
        assertEquals(2, comboReturnGround.getActions().size());
        assertEquals(Direction.WEST, comboReturnGround.get(0).getDirection());
        assertEquals(Direction.SOUTH, comboReturnGround.get(1).getDirection());
    }
}
