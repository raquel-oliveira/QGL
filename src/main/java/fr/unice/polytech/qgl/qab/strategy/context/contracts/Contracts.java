package fr.unice.polytech.qgl.qab.strategy.context.contracts;

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
 * @version 21/03/16.
 */
public class Contracts {
    private static final Logger LOGGER = LogManager.getLogger(Contracts.class);

    private List<ContractItem> items;
    private Boolean completeContracts;
    private Map<String, Integer> collectedResources;


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
     * Get number of amount of a primaryResource needed to fill the contracts that need of 'resource'(primary + maufactured)
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
        Set<String> primaryResources = primaryNeeded();
        for (String resource: primaryResources) {
            if (!getCollectedResources().containsKey(resource))
                return false;
            if (getCollectedResources().get(resource) <  getAmountPrimaryNeeded(new PrimaryResource(PrimaryType.valueOf(resource)))){
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
     * @return list of primary resources needed to complet all the contracts
     */
    public Set<String> primaryNeeded(){
        Set<String> primaryResource = new HashSet<String>();

        for (int i = 0; i < items.size(); i++){
            if (items.get(i).resource() instanceof PrimaryResource){
                primaryResource.add(items.get(i).resource().getName());
            }
            else{
                for (PrimaryType itemRecipe: ((ManufacturedResource) items.get(i).resource()).getRecipe(0).keySet()) {
                    primaryResource.add(new PrimaryResource(itemRecipe).getName());
                }
            }
        }
        return primaryResource;
    }

    /**
     * Return the index of the contract item that have the resource.
     * @param resource
     * @return
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
     * Return the collected Resources that were tooked after exploit a tile
     * @return HashMap - resources collected and the respective amounts.
     */
    public  Map<String, Integer> getCollectedResources(){
        return collectedResources;
    }

    /**
     * Method to add a collect resource after action exploit.
     * @param resource
     * @param amount
     */
    public void addCollectedResources(Resource resource, int amount) {
        if (collectedResources.containsKey(resource.getName())) {
            collectedResources.put(resource.getName(), collectedResources.get(resource.getName()) + amount);
        } else {
            collectedResources.put(resource.getName(), amount);
        }
        LOGGER.info("Collected resources: " + collectedResources);
    }

    /**
     * Method to Remove a quantity of a resource. To use to update after transform.
     * @param resource
     * @param amount
     */
    public int decreaseAmountOfCollectedResources(Resource resource, int amount) {
        try{
            int newAmount = collectedResources.get(resource.getName()) - amount;
            if( newAmount >= 0){
                collectedResources.put(resource.getName(), newAmount);
                return amount;
            }
            else {
                newAmount = 0;
                int beforeDecrease = collectedResources.get(resource.getName());
                collectedResources.put(resource.getName(), newAmount);
                return beforeDecrease;
            }
        }catch (Exception e){
            return -1;
        }
    }

}