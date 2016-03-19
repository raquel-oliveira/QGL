package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;

import java.util.List;

/**
 * version on 02/03/2016.
 */
public class TransformResponse {
    private ManufacturedResource resource;
    private int amount;

    public TransformResponse(){
        resource = null;
        amount = 0;
    }
    public void addData(ManufacturedResource res, int amount, Context context) {
        this.resource = res;
        this.amount = amount;

        //Add the transformed resource in collected resources
        context.addCollectedResources(res, amount);
        //Decrease primary amounts used to create
        for(java.util.Map.Entry<PrimaryType, Integer> ingredientRecipe : res.getRecipe(amount).entrySet()) {
            PrimaryResource primary = new PrimaryResource(ingredientRecipe.getKey());
            context.decreaseAmountOfCollectedResources(primary, ingredientRecipe.getValue());
        }
    }

    public Resource getResource(){
        return resource;
    }

    public int getAmount(){
        return amount;
    }
}
