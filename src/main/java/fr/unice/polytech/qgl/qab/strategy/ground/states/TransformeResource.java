package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Transform;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;

import java.util.EnumMap;
import java.util.List;

/**
 * version 07/03/2016.
 */
public class TransformeResource extends GroundState {

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (context.getResourcesToCreate().isEmpty()) {
            updateContext(context);
            return new StopSimulation();
        } else {
            return new TransformeResource();
        }
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        List<ContractItem> contracts = context.getContracts();

        EnumMap<PrimaryType, Integer> recipe = new EnumMap<PrimaryType, Integer>(PrimaryType.class);

        ManufacturedResource res = context.getResourcesToCreate().get(0);
        for(java.util.Map.Entry<PrimaryType, Integer> ingredientRecipe : ((ManufacturedResource) contracts.get(contracts.indexOf(res)).resource()).getRecipe(0).entrySet()) {
            if(context.getContracts().contains(ingredientRecipe.getKey())){
                recipe.put(ingredientRecipe.getKey(), context.getQtdToUse(res, new PrimaryResource(ingredientRecipe.getKey())));
            }
        }


        Action act = new Transform(recipe);
        context.getResourcesToCreate().remove(0);
        context.current().setLastAction(act);
        return act;
    }

    /**
     * Method to updata the context
     * @param context
     */
    private static void updateContext(Context context) {
        context.current().setLastAction(null);
        context.current().setComboAction(null);
    }
}
