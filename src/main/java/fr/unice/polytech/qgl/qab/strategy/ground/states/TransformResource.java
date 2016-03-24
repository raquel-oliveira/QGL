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
        if (context.getContracts().getResourcesToCreate().isEmpty()) {
            updateContext(context);
            return new StopSimulation();
        } else {
            return GroundStateFactory.buildState(GroundStateType.TRANSFORM);
        }
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Contracts contracts = context.getContracts();
        List<ContractItem> contractItems = contracts.getItems();

        if (context.getContracts().getResourcesToCreate().isEmpty()){
            return new Stop();
        }
        //Element that we are going to try to create. If he can not create, take the next.
        ManufacturedResource res = context.getContracts().getResourcesToCreate().get(0);
        if(!contractItems.get(contracts.getContractIndex(res)).canTransform(context)){
            do{
                LOGGER.info("can not make "+res.getName());
                context.getContracts().removeResourceToCreate(0);
                if (context.getContracts().getResourcesToCreate().isEmpty()) {
                    LOGGER.info("There is not anymore in the list to try");
                    return new Stop();
                }
                else{
                    res = context.getContracts().getResourcesToCreate().get(0);
                    LOGGER.info("Try to "+res.getName());
                }
            } while(!contractItems.get(contracts.getContractIndex(res)).canTransform(context));
        }
        // Update that this manufactured was already "created"
        ((ManufacturedResource)(contractItems.get(contracts.getContractIndex(res)).resource())).setTransformed(true);
        //Amounted asked in the contract
        int amountContract = contractItems.get(contracts.getContractIndex(res)).amount();
        java.util.Map recipe = ((ManufacturedResource) contractItems.get(contracts.getContractIndex(res)).resource()).getRecipe(amountContract);

        LOGGER.info("Transform " + res.getName());
        Action act = new Transform(recipe);

        context.getContracts().removeResourceToCreate(0);

        context.current().setLastAction(act);
        return act;
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