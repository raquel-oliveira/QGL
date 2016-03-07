package fr.unice.polytech.qgl.qab.strategy.context.utils;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;

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

    public Boolean isComplete(Map<String, Integer> collectedResources){
       if (resource instanceof PrimaryResource) {
           if (collectedResources.containsKey(resource.getName()) &&
                   collectedResources.get(resource.getName()) >= amount) {
                   completeContract = true;
           }
           return completeContract;
       }
       else if(resource instanceof ManufacturedResource){
           //Todo: Implement taking in count count after transform
       }

        return completeContract;
    }
}

