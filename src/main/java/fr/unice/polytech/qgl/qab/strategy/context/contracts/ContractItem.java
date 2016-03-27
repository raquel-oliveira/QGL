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
 * Class that represents the items of the contract
 * @version 16/12/15.
 */
public class ContractItem {
    private Resource resource;
    private int amount;
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

    /**
     * @param collectedResources Map with the information of the resources available/took.
     * @return true if the contract was filled.
     * */
    public Boolean isComplete(Map<Resource, Integer> collectedResources){
       return collectedResources.containsKey(resource) && collectedResources.get(resource) >= amount;
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
            return false;
        }
        //Or is manufactured
        else {
            if(isComplete(contracts.getCollectedResources())){
                //This contract it was already fill.
                LOGGER.info("Already transform:"+ this.resource.getName() + " Asked: " + this.amount() + " have: " + contracts.getCollectedResources().get(resource));
                return false;
            }
            else{
                Map<PrimaryType, Integer> recipe = ((ManufacturedResource)this.resource).getRecipe((int) (ceil(amount * MARGIN_ERROR)));
                for(Map.Entry<PrimaryType, Integer> getRecipe : recipe.entrySet()){
                    PrimaryResource res = new PrimaryResource(getRecipe.getKey());
                    //Does not have primary to create the resource.
                    if (!contracts.getCollectedResources().containsKey(res)) {
                        return false;
                    }
                    if(contracts.getCollectedResources().get(res) < recipe.get(res.getType())){
                        return false;
                    }
                }
                return true;
            }
        }
    }

    /**
     *
     * @return the margin error referent to the production of a Resource.
     */
    public static double getMarginError() {
        return MARGIN_ERROR;
    }
}

