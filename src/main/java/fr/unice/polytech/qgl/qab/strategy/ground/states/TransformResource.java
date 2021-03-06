package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Transform;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;

import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.ContractItem;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.Contracts;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateFactory;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.Math.ceil;


import java.util.List;

/**
 * This GroundState represents the state to transforme the resources.
 * version 07/03/2016.
 */
public class TransformResource extends GroundState {
    private static final Logger LOGGER = LogManager.getLogger(TransformResource.class);
    private static final int BUDGET_TRANSFORM = 800;

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        Contracts contracts = context.getContracts();
        if (!contracts.enoughToTransform()) {
            updateContext(context);
            return GroundStateFactory.buildState(GroundStateType.SCOUT_TILE);
        } else {
            return GroundStateFactory.buildState(GroundStateType.TRANSFORM);
        }
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Contracts contracts = context.getContracts();
        List<ContractItem> contractItems = contracts.getItems();

        //Everything was completed
        if (context.getContracts().contractsAreComplete()){
            return new Stop();
        }
        //There is at least one element that can be transformed.
        if(contracts.enoughToTransform()) {
            for(ContractItem items : contractItems){
                //This element can be transformed:
                //TODO: This condition "items.resource().getType() != ManufacturedType.INGOT" should be treated with the issue #QAB-198
                if (items.canTransform(contracts) && context.getBudget() >= BUDGET_TRANSFORM && items.resource().getType() != ManufacturedType.INGOT){
                    LOGGER.info("Can transform");
                    //Amounted asked in the contract
                    java.util.Map recipe = ((ManufacturedResource)(items.resource())).getRecipe( (int) (ceil(items.amount() * ContractItem.getMarginError())));

                    LOGGER.info("Transform " + items.resource().getName());
                    Action act = new Transform(recipe, context);

                    context.current().setLastAction(act);
                    return act;
                }
            }
        }
        //Never will get in the action because of the verifications in groundStrategy.
        return context.current().getLastAction();
    }

    /**
     * Method to update the context
     * @param context
     */
    private static void updateContext(Context context) {
        context.current().setLastAction(null);
        context.current().setComboAction(null);
    }

}