package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Transform;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.ContractItem;
import static java.lang.Math.ceil;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
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
    public void testReturnState() throws NegativeBudgetException, PositionOutOfMapRange{
        context.getContracts().addContract("WOOD", 15);
        context.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.FISH), 10);

        GroundState state = trans.getState(context, new Map());
        assertEquals(ScoutTile.class, state.getClass());

        context.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.WOOD), 15);
        state = trans.getState(context, new Map());
        assertEquals(ScoutTile.class, state.getClass());

        context.getContracts().addContract("GLASS", 1);
        context.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.QUARTZ), new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int)(ceil(1 * ContractItem.getMarginError()))).get(PrimaryType.QUARTZ));
        context.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.WOOD), new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int)(ceil(1 * ContractItem.getMarginError()))).get(PrimaryType.WOOD));
        state = trans.getState(context, new Map());
        assertEquals(TransformResource.class, state.getClass());

    }

    @Test
    public void responseStop() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        context.getContracts().addContract("GLASS", 1);
        context.getContracts().addCollectedResources(new ManufacturedResource(ManufacturedType.GLASS), 1);
        Action act = trans.responseState(context, new Map());
        assertEquals(Stop.class, act.getClass());
    }

    @Test
    public void responseTransforme() throws NegativeBudgetException, IndexOutOfBoundsComboAction {
        context.setBudget(1000);
        context.getContracts().addContract("GLASS", 5);
        context.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.QUARTZ), new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int)(ceil(5 * ContractItem.getMarginError()))).get(PrimaryType.QUARTZ));
        context.getContracts().addCollectedResources(new PrimaryResource(PrimaryType.WOOD), new ManufacturedResource(ManufacturedType.GLASS).getRecipe((int)(ceil(5 * ContractItem.getMarginError()))).get(PrimaryType.WOOD));
        Action act = trans.responseState(context, new Map());
        assertEquals(Transform.class, act.getClass());
    }

}
