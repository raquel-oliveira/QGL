package fr.unice.polytech.qgl.qab.strategy.context.contracts;

import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Everything related or that affect directly the contracts/contractItem.
 * @version 21/03/16.
 */
public class Contracts {
    private static final Logger LOGGER = LogManager.getLogger(Contracts.class);

    private List<ContractItem> items;
    private Boolean completeContract;
    private List<ManufacturedResource> resourcesToCreate;

    public Contracts() {
        this.items = new ArrayList<>();
        this.completeContract = false;
        this.resourcesToCreate = null;
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
            //update the resources manufactured in the list of resources to be create.
            if (getResourcesToCreate() == null)
                setResourcesToCreate(new ArrayList<>());
            getResourcesToCreate().add(new ManufacturedResource(ManufacturedType.valueOf(resource)));
        } catch (Exception ex) {
            items.add(new ContractItem(new PrimaryResource(PrimaryType.valueOf(resource)), amount));
        }
    }

    /**
     * Get number of amount of a primaryResource needed to fill the contracts that need of 'resource'(primary + maufactured)
     * @param resource
     * @return
     */
    public int getAccumulatedAmountNecessary(Resource resource){
        int amount = 0;
        if (!resource.isPrimary()){
            LOGGER.error("Passed the wrong parameter.");
            return -1;
        }
        for (int i = 0; i < items.size(); i++) {
            Resource res = items.get(i).resource();
            int amountAsked = items.get(i).amount();
            if ((res.isPrimary()) && res.getName().equals(resource.getName())) {
                amount += amountAsked;
            } else if (!(res.isPrimary())){
                PrimaryType prim = ((PrimaryResource)(resource)).getType();
                if (((ManufacturedResource) items.get(i).resource()).getRecipe(0).containsKey(prim)){
                    int pureAmount = ((ManufacturedResource) res).getRecipe(items.get(i).amount()).get(prim);
                    amount +=  pureAmount;
                }
            }
        }
        LOGGER.info("The quantity necessary of "+ resource.getName()+ " is " + amount);
        return amount;
    }

    /**
     * @return true if all the contracts are completed.
     */
    public boolean contractsAreComplete(Context context){
        completeContract = true;
        for(int i = 0; i < items.size(); i++){
            if (!items.get(i).isComplete(context.getCollectedResources())){
                completeContract = false;
                return completeContract;
            }
        }
        return completeContract;
    }

    /**
     * @return true if there is primary resources enough to complete all the contracts
     * Observation: It reserves the quantity of primary to complete the primaries resources
     */
    public boolean enoughToTransformAll(Context context){
        Set<String> primaryResources = primaryNeeded();
        for (String resource: primaryResources) {
            if (!context.getCollectedResources().containsKey(resource))
                return false;
            if (context.getCollectedResources().get(resource) < getAccumulatedAmountNecessary(new PrimaryResource(PrimaryType.valueOf(resource)))){
                return false;
            }
        }
        LOGGER.error("Enough to transform "+ primaryResources+"");
        return true;
    }

    /**
     * @return true if there is primary resources enough to complete at least one contract manufactured
     */
    public boolean enoughToTransform(Context context){
        List<ManufacturedResource> listManufactures = getResourcesToCreate();
        for (int i = 0; i < listManufactures.size(); i++){
            if (items.get(getContractIndex(listManufactures.get(i))).canTransform(context)){
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
            if (items.get(i).resource().isPrimary()){
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

    public List<ManufacturedResource> getResourcesToCreate() {
        return resourcesToCreate;
    }

    public void setResourcesToCreate(List<ManufacturedResource> resourcesToCreate) {
        this.resourcesToCreate = resourcesToCreate;
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