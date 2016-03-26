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

import java.util.List;

/**
 * version 07/03/2016.
 */
public class TransformResource extends GroundState {
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

        if (context.getContracts().getResourcesToCreate() == null || context.getContracts().getResourcesToCreate().isEmpty())
            return new Stop();

        //Element that we are going to try to create. If he can not create, take the next.
        ManufacturedResource res = context.getContracts().getResourcesToCreate().get(0);
        //while(!contracts.get(context.getContractIndex(res)).CanTransform()){
          //  context.removeResourceToCreate(0);
            if (context.getContracts().getResourcesToCreate().isEmpty()) {
                return new Stop();
            }
            else{
                res = context.getContracts().getResourcesToCreate().get(0);
            }
       // }
        // Update that this manufactured was already "created"
        ((ManufacturedResource)(contracts.getItems().get(context.getContracts().getContractIndex(res)).resource())).setTransformed(true);
        //Amounted asked in the contract
        int amountContract = contracts.getItems().get(context.getContracts().getContractIndex(res)).amount();
        //TODO: update this to send the right value.
        java.util.Map recipe = ((ManufacturedResource) contracts.getItems().get(context.getContracts().getContractIndex(res)).resource()).getRecipe(amountContract);



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