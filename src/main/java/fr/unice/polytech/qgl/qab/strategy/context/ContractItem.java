package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;

import java.util.List;
import java.util.Map;

/**
 * @version 16/12/15.
 *
 * Class that represents the items of the contract
 */
public class ContractItem {
    private Resource resource;
    private int amount;
    private int accumulated; //TODO: Delete this
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
        this.accumulated = 0; //TODO: delete this
        completeContract = false;
    }

    /**
     * Get the value amount
     * @return value amount
     */
    public int amount() {
        return this.amount;
    }

    //TODO: delete this method
    /**
     * Get the value accumulated
     * @return value accumulated
     */
    public int accumulated() {
        return this.accumulated;
    }

    //TODO: delete this method
    /**
     * Update the value accumulated after collect some resource
     * @param amount
     * @throws NegativeBudgetException
     */
    public void collect(int amount) throws NegativeBudgetException {
        int temporaryAmount = this.accumulated() + amount;
        if(temporaryAmount < 0)
            throw new NegativeBudgetException("The value collected can no be negative.");
        this.accumulated = temporaryAmount;
    }


    /**
     * Get the resource
     * @return resource
     */
    public Resource resource() {
        return this.resource;
    }

    public Boolean isComplete(Map<String, Integer> collectedResources){
       if( resource instanceof PrimaryResource){
           if(collectedResources.containsKey(resource.getName())){
               if(collectedResources.get(resource.getName()) >= amount){ completeContract = true;}
           }
           return completeContract;
       }
       else if( resource instanceof ManufacturedResource){
           //Todo: Implement taking in count count after transform
       }

        return completeContract;
    }



}
