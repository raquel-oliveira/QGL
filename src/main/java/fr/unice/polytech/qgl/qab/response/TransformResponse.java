package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * Class that represent the Transform response structure
 * version on 02/03/2016.
 */
public class TransformResponse {
    private ManufacturedResource resource;
    private int amount;

    /**
     * TransformResponse's constructor
     */
    public TransformResponse(){
        resource = null;
        amount = 0;
    }

    /**
     * Method that add resource in the set of resources
     * @param res transformed resource
     * @param amount transformed amout
     * @param context current context data
     */
    public void addData(ManufacturedResource res, int amount, Context context) {
        this.resource = res;
        this.amount = amount;

        //Add the transformed resource in collected resources
        context.getContracts().addCollectedResources(res, amount);
    }

    /**
     * Return the transformed resource
     * @returnthe transformed resource
     */
    public Resource getResource(){
        return resource;
    }

    /**
     * Return the transformed amount
     * @return transformed amount
     */
    public int getAmount(){
        return amount;
    }
}
