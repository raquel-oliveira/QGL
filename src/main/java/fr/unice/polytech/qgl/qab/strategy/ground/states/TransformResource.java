package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Transform;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;

import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.ContractItem;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.Contracts;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateFactory;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * version 07/03/2016.
 */
public class TransformResource extends GroundState {
    private static final Logger LOGGER = LogManager.getLogger(TransformResource.class);


    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (!context.getContracts().enoughToTransform(context)) {
            updateContext(context);
            LOGGER.info("Will scout");
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
        if (context.getContracts().contractsAreComplete(context)){
            return new Stop();
        }
        //There is at least one element that can be transformed.
        if(contracts.enoughToTransform(context)) {
            for(ContractItem items : contractItems){
                //This element can be transformed:
                if (items.canTransform(context)){
                    //Amounted asked in the contract
                    int amountContract = items.amount();
                    java.util.Map recipe = ((ManufacturedResource)(items.resource())).getRecipe(amountContract);

                    LOGGER.info("Transform " + items.resource().getName());
                    Action act = new Transform(recipe);

                    context.current().setLastAction(act);
                    return act;
                }
            }
        }
        //Retorna procurar mais materiais? Verificar essa condicao abaixo:
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