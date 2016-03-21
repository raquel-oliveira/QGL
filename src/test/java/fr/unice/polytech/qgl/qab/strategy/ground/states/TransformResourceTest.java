package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * version 14/03/2016.
 */
public class TransformResourceTest {
    TransformResource trans;

    @Before
    public void defineContext() {
        trans = new TransformResource();
    }

    @Test
    public void testInstance() {
        TransformResource transforme = new TransformResource();
        assertEquals(trans.getClass(), transforme.getClass());
    }

    @Test
    public void testActions() throws NegativeBudgetException, PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        GroundState state = trans.getState(new Context(), new Map());
    }
}