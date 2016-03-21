package fr.unice.polytech.qgl.qab.strategy.context.contracts;

import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * @version 21/03/16.
 */
public class Contracts {
    private static final Logger LOGGER = LogManager.getLogger(Contracts.class);

    private List<ContractItem> contracts;
    private Map<String, Integer> collectedResources;
    private List<ManufacturedResource> resourcesToCreate;
    private Boolean completeContract;

    public Contracts() {
        this.contracts = new ArrayList<>();
        this.collectedResources = new HashMap<>();
        this.resourcesToCreate = null;
        this.completeContract = false;
    }

    public List<ContractItem> getItems() {
        return this.contracts;
    }

    /**
     *
     * @return list of primary resources needed to complet all the contracts
     * //TODO: Look if there is similar codes.Exemple: addResources in ContextAnalyze.
     */
    public Set<String> primaryNeeded() {
        Set<String> primaryResource = new HashSet<String>();

        for (int i = 0; i < contracts.size(); i++){
            if (contracts.get(i).resource().isPrimary()){ // tamo na atividads
                primaryResource.add(contracts.get(i).resource().getName());
            }
            else{
                for (PrimaryType itemRecipe: ((ManufacturedResource) contracts.get(i).resource()).getRecipe(0).keySet()) {
                    primaryResource.add(new PrimaryResource(itemRecipe).getName());
                }
            }
        }
        return primaryResource;
    }

    /**
     * @return true if there is primary resources enough to complete all the contracts
     */
    public boolean enoughToTransform(){
        boolean enough = true;
        Set<String> primaryResources = primaryNeeded();
        for (String resource: primaryResources) {
            if (!collectedResources.containsKey(resource))
                return false;
            if (collectedResources.get(resource) < getAccumulatedAmountNecessary(new PrimaryResource(PrimaryType.valueOf(resource)))){
                return enough = false;
            }
        }
        return enough;
    }

    /**
     * Get number of amount of a primaryResource needed to fill the contracts that need of 'resource'(primary + maufactured)
     * @param resource
     * @return
     */
    public int getAccumulatedAmountNecessary(Resource resource) {
        int amount = 0;
        if (!resource.isPrimary()){
            LOGGER.error("Passed the wrong parameter.");
            return -1;
        }
        for (int i = 0; i < contracts.size(); i++) {
            if ((contracts.get(i).resource().isPrimary())
                    && contracts.get(i).resource().getName().equals(resource.getName())) {
                amount += contracts.get(i).amount();
            } else if (!(contracts.get(i).resource().isPrimary())){
                PrimaryType prim = ((PrimaryResource)(resource)).getType();
                if (((ManufacturedResource) contracts.get(i).resource()).getRecipe(0).containsKey(prim)){
                    amount += ((ManufacturedResource) contracts.get(i).resource()).getRecipe(contracts.get(i).amount()).get(prim);
                }
            }
        }
        return amount;
    }

    /**
     * This method is to verify how much of amount can be used of the resource to create manufatured.
     * @param manufatured to be create
     * @param resource to be used to create manufatured
     * @return quantity of resource to be used to create a manufactured.
     * @return -1 this resource its not in the recipe of the first parameter.
     */
    public int getQtdToUse(ManufacturedResource manufatured, Resource resource) {
        //Check if resource is parte of manufatured recipe
        if(!manufatured.getRecipe(0).containsKey(resource)){
            LOGGER.error("error:", "trying to get amount of a primary resource that is not necessary to create the transform,");
            return -1;
        }

        int amount = getAccumulatedAmountNecessary(resource);

        for (int i = 0; i < contracts.size(); i++){
            Resource res = contracts.get(i).resource();
            if(res.isPrimary()){
                if(res.getName().equals(resource.getName()) && contracts.get(i).isComplete(collectedResources)){
                    //Not use amount of a primary resource already complete
                    amount -= contracts.get(i).amount();
                }
            }
            else{
                //If it was not made a transform yet to this res and its not the manufatured I want.
                if (getResourcesToCreate().contains(res) && !manufatured.equals((ManufacturedResource)res)){
                    //TODO: verify best strategy to use all the primary resource left to make manufatured resources
                    amount -= ((ManufacturedResource) res).getRecipe(contracts.get(i).amount()).get(resource);

                }
            }
        }
        return amount;
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
                //LOGGER.error("error: Try to decrease a quantity of "+ resource.getName() + " more than you collected");
                newAmount = 0;
                int beforeDecrease = collectedResources.get(resource.getName());
                collectedResources.put(resource.getName(), newAmount);
                return beforeDecrease;
            }
        }catch (Exception e){
            //LOGGER.error(ERROR,e);
            return -1;
        }
    }

    /**
     * Add items in the contract
     * @param resource resource's name
     * @param amount resource's amount
     * @throws NegativeBudgetException
     */
    public void addContract(String resource, int amount) throws NegativeBudgetException {
        try {
            contracts.add(new ContractItem(new ManufacturedResource(ManufacturedType.valueOf(resource)), amount));
            //update the resources manufactured in the list of resources to be create.
            if (resourcesToCreate == null)
                resourcesToCreate = new ArrayList<>();
            resourcesToCreate.add(new ManufacturedResource(ManufacturedType.valueOf(resource)));
        } catch (Exception ex) {
            contracts.add(new ContractItem(new PrimaryResource(PrimaryType.valueOf(resource)), amount));
        }
    }

    /**
     * Return the index of the contract item that have the resource.
     * @param resource
     * @return
     */
    public int getContractIndex(Resource resource) {
        for (int index = 0; index < contracts.size(); index++) {
            ContractItem item = contracts.get(index);
            if (item.resource().getName().equals(resource.getName())){
                return index;
            }
        }
        LOGGER.error("The element" + resource.getName() + "its not in the contract.");
        return -1;
    }

    /**
     * @return true if all the contracts are completed.
     */
    public boolean contractsAreComplete(){
        completeContract = true;
        for(int i = 0; i < contracts.size(); i++){
            if (!contracts.get(i).isComplete(collectedResources)){
                completeContract = false;
                return completeContract;
            }
        }
        return completeContract;
    }

    /**
     *
     * @return List of resources in the contract that need to be transformed
     */
    public List<ManufacturedResource> getResourcesToCreate() {
        return resourcesToCreate;
    }

    public void removeResourceToCreate(int index){
        if(resourcesToCreate.isEmpty()){
            LOGGER.error("error:", "The list is empty, can not remove.");
        }
        try{
            resourcesToCreate.remove(index);
        }
        catch(Exception e){
            LOGGER.error("Can not remove this element");
        }
    }
}
