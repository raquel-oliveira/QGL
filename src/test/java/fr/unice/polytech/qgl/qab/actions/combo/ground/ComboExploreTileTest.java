package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by huang on 20/03/16.
 */
public class ComboExploreTileTest {
    ComboExploreTile comboExploreTile;

    @Before
    public void defineCombo(){
        comboExploreTile = new ComboExploreTile();
    }

    @Test
    public void defineActionTest() throws IndexOutOfBoundsComboAction {
        comboExploreTile.defineActions();
        assertEquals(comboExploreTile.get(0).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(1).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(1).getDirection(), Direction.EAST);
        assertEquals(comboExploreTile.get(2).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(3).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(3).getDirection(), Direction.SOUTH);
        assertEquals(comboExploreTile.get(4).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(5).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(5).getDirection(), Direction.WEST);
        assertEquals(comboExploreTile.get(6).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(7).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(7).getDirection(), Direction.WEST);
        assertEquals(comboExploreTile.get(8).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(9).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(9).getDirection(), Direction.NORTH);
        assertEquals(comboExploreTile.get(10).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(11).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(11).getDirection(), Direction.NORTH);
        assertEquals(comboExploreTile.get(12).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(13).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(13).getDirection(), Direction.EAST);
        assertEquals(comboExploreTile.get(14).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(15).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(15).getDirection(), Direction.EAST);
        assertEquals(comboExploreTile.get(16).getClass(), Explore.class);

        assertEquals(comboExploreTile.get(17).getClass(), MoveTo.class);
        assertEquals(comboExploreTile.get(17).getDirection(), Direction.SOUTH);

        assertEquals(comboExploreTile.get(18).getClass(),MoveTo.class);
        assertEquals(comboExploreTile.get(18).getDirection(), Direction.WEST);

    }
}
