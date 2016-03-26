package fr.unice.polytech.qgl.qab.strategy.context.contracts;

import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.Math.ceil;


import java.util.Map;

/**
 * @version 16/12/15.
 *
 * Class that represents the items of the contract
 */
public class ContractItem {
    private Resource resource;
    private int amount;
    private boolean completeContract;
    private boolean canTransform;
    private static final double MARGIN_ERROR = (double)10/9;
    private static final Logger LOGGER = LogManager.getLogger(ContractItem.class);

    /**
     * ContractItem's constructor
     * @param resource the item (resource)
     * @param amount quantity of resources
     * @throws NegativeBudgetException
     */
    public ContractItem(Resource resource, int amount) throws NegativeBudgetException {
        if (amount < 0)
            throw new NegativeBudgetException("The value to initial amount to the resource can not be negative.");
        this.resource = resource;
        this.amount = amount;
        completeContract = false;
        canTransform = false;
    }

    /**
     * Get the value amount
     * @return value amount
     */
    public int amount() {
        return this.amount;
    }

    /**
     * Get the resource
     * @return resource
     */
    public Resource resource() {
        return this.resource;
    }

    public Boolean isComplete(Map<Resource, Integer> collectedResources){
       if(collectedResources.containsKey(resource) && collectedResources.get(resource) >= amount){
            completeContract = true;
       }
        return completeContract;
    }

    /**
     *
     * @return false if this contract its of typePrimary,
     * or if there is not enough quantity to fill the contract(complete).
     * @return true if he can(has the possibility, that changes depending with the difficult) transform
     * the amount necessary asked in the contract.
     * Observation: If it need to take amount of a contract of a primaryType to complete this contract(if manufactured),
     * it will. Priority to manufactured>primary.
     */
    public boolean canTransform(Contracts contracts) {
        //Can not transform a primary Resource.
        if(resource() instanceof PrimaryResource){
            canTransform = false;
            return canTransform;
        }
        //Or is manufactured
        else {
            if(isComplete(contracts.getCollectedResources())){
                //This contract it was already fill.
                LOGGER.info("Already transform:"+ this.resource.getName() + " Asked: " + this.amount() + " have: " + contracts.getCollectedResources().get(resource));
                canTransform = false;
                return canTransform;
            }
            else{
                Map<PrimaryType, Integer> recipe = ((ManufacturedResource)this.resource).getRecipe((int) (ceil(amount * MARGIN_ERROR)));
                for(Map.Entry<PrimaryType, Integer> getRecipe : recipe.entrySet()){
                    PrimaryResource res = new PrimaryResource(getRecipe.getKey());
                    //Does not have primary to create the resource.
                    if (!contracts.getCollectedResources().containsKey(res)) {
                        canTransform = false;
                        return canTransform;
                    }
                    if(contracts.getCollectedResources().get(res) < recipe.get(res.getType())){
                        canTransform = false;
                        return canTransform;
                    }
                }
                canTransform = true;
                return canTransform;
            }
        }
    }

    public static double getMarginError() {
        return MARGIN_ERROR;
    }
}

