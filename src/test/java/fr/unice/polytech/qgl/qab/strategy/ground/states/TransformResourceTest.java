package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Exploit;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * version 14/03/2016.
 */
public class TransformResourceTest {
    TransformResource trans;
    Context context;

    @Before
    public void defineContext() throws NegativeBudgetException{
        trans = new TransformResource();
        context = new Context();
    }

    @Test
    public void testInstance() {
        TransformResource transforme = new TransformResource();
        assertEquals(trans.getClass(), transforme.getClass());
    }

    @Test
    public void testReturnScout() throws NegativeBudgetException, PositionOutOfMapRange{
        context.getContracts().addContract("WOOD", 15);
        context.addCollectedResources(new PrimaryResource(PrimaryType.FISH), 10);

        GroundState state = trans.getState(context, new Map());
        assertEquals(ScoutTile.class, state.getClass());
    }
}
