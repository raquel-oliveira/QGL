package fr.unice.polytech.qgl.qab.strategy.context.contracts;

import fr.unice.polytech.qgl.qab.exception.context.InsufficientException;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.Math.ceil;


import java.util.*;

/**
 * Everything related or that affect directly the contracts/contractItem.
 * Contracts Handle.
 * @version 21/03/16.
 */
public class Contracts {
    private static final Logger LOGGER = LogManager.getLogger(Contracts.class);

    private List<ContractItem> items;
    private Boolean completeContracts;
    private Map<Resource, Integer> collectedResources;


    public Contracts() {
        this.items = new ArrayList<>();
        this.completeContracts = false;
        this.collectedResources = new HashMap<>();

    }

    public List<ContractItem> getItems() {
        return this.items;
    }

    /**
     * Add items in the contract
     * @param resource resource's name
     * @param amount resource's amount
     * @throws NegativeBudgetException
     */
    public void addContract(String resource, int amount) throws NegativeBudgetException {
        try {
            items.add(new ContractItem(new ManufacturedResource(ManufacturedType.valueOf(resource)), amount));
        } catch (Exception ex) {
            items.add(new ContractItem(new PrimaryResource(PrimaryType.valueOf(resource)), amount));
        }
    }

    /**
     * Get number of amount of a primaryResource needed to fill the contracts (primary and manufactured).
     * @param resource
     * @return
     */
    public int getAmountPrimaryNeeded(PrimaryResource resource){
        int amount = 0;
        for (int i = 0; i < items.size(); i++) {
            Resource res = items.get(i).resource();
            int amountAsked = items.get(i).amount();
            if ((res instanceof PrimaryResource) && res.getName().equals(resource.getName())) {
                amount += amountAsked;
            } else if ((res instanceof ManufacturedResource) && (!items.get(i).isComplete(getCollectedResources()))){
                PrimaryType prim = resource.getType();
                if (((ManufacturedResource) items.get(i).resource()).getRecipe(0).containsKey(prim)){
                    int amountAprox = ((ManufacturedResource) res).getRecipe((int) (ceil(items.get(i).amount() * ContractItem.getMarginError()))).get(prim);
                    amount +=  amountAprox;
                }
            }
        }
        return amount;
    }

    /**
     * @return true if all the contracts are completed.
     */
    public boolean contractsAreComplete(){
        completeContracts = true;
        for(int i = 0; i < items.size(); i++){
            if (!items.get(i).isComplete(getCollectedResources())){
                completeContracts = false;
                return completeContracts;
            }
        }
        return completeContracts;
    }

    /**
     * @return true if there is primary resources enough to complete all the contracts
     * Observation: It reserves the quantity of primary to complete the primaries resources
     */
    public boolean enoughToTransformAll(){
        Set<PrimaryResource> primaryResources = primaryNeeded();
        for (PrimaryResource resource: primaryResources) {
            if (!getCollectedResources().containsKey(resource))
                return false;
            if (getCollectedResources().get(resource) <  getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.valueOf(resource.getName())))){
                return false;
            }
        }
        LOGGER.info("Enough to transform "+ primaryResources+"");
        return true;
    }

    /**
     * @return true if there is primary resources enough to complete at least one contract manufactured
     */
    public boolean enoughToTransform(){
        for(ContractItem contracts : items){
            if (contracts.canTransform(this)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return list of primary resources needed
     * to complete all the contracts
     */
    public Set<PrimaryResource> primaryNeeded(){
        Set<PrimaryResource> primaryResource = new HashSet<PrimaryResource>();

        for (int i = 0; i < items.size(); i++){
            if(!items.get(i).isComplete(getCollectedResources())){
                if (items.get(i).resource() instanceof PrimaryResource){
                    primaryResource.add((PrimaryResource) items.get(i).resource());
                }
                else{
                    for (PrimaryType itemRecipe: ((ManufacturedResource) items.get(i).resource()).getRecipe(0).keySet()) {
                        primaryResource.add(new PrimaryResource(itemRecipe));
                    }
                }
            }
        }
        return primaryResource;
    }

    /**
     * Return the index of the contract item that have the param resource.
     * @param resource
     * @return the index of the contract with the param resource.
     */
    public int getContractIndex(Resource resource) {
        for (int index = 0; index < items.size(); index++) {
            ContractItem item = items.get(index);
            if (item.resource().getName().equals(resource.getName())){
                return index;
            }
        }
        LOGGER.error("The element" + resource.getName() + "its not in the contract.");
        return -1;
    }

    /**
     * Return the collected Resources
     * @return HashMap - resources collected and the respective amounts.
     */
    public  Map<Resource, Integer> getCollectedResources(){
        return collectedResources;
    }

    /**
     * Method to add a collect resource after action exploit or transform.
     * @param resource
     * @param amount
     */
    public void addCollectedResources(Resource resource, int amount) {
        if (collectedResources.containsKey(resource)) {
            LOGGER.info("Add "  + resource.getName() + "more: " + amount + "new: " + (amount+ collectedResources.get(resource)));
            collectedResources.put(resource, collectedResources.get(resource) + amount);
        } else {
            LOGGER.info("Add "  + resource.getName() + " with " + amount);
            collectedResources.put(resource, amount);
        }
    }

    /**
     * Method to Remove a quantity of a resource. To use to update after transform.
     * @param resource
     * @param amount
     * @return the amount it decrease. Same of parameter.
     * @throws InsufficientException
     */
    public int decreaseAmountOfCollectedResources(Resource resource, int amount) throws InsufficientException {
        int newAmount = collectedResources.get(resource) - amount;
        if( newAmount >= 0){
            collectedResources.put(resource, newAmount);
            return amount;
        } else {
            throw new InsufficientException("Can not decrease " + amount + " of " + resource.getName() + " . There is only " + collectedResources.get(resource));
        }
    }
}