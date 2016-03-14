package fr.unice.polytech.qgl.qab.strategy.context;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.utils.Budget;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContextAction;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import fr.unice.polytech.qgl.qab.util.Discovery;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * @version 16/12/15.
 *
 * Class that represents the context of the simulation.
 */
public class Context {
    private int men;
    private boolean status;
    private Budget budget;
    private List<ContractItem> contracts;
    private Map<String, Integer> collectedResources;
    private List<ManufacturedResource> resourcesToCreate;
    private Boolean completeContract;

    // direction of the head in the begin
    private Direction firstHead;
    // direction of the current head
    private Direction heading;
    // last discovery
    private Discovery lastDiscovery;
    // context current to states use
    private ContextAction contextActionCurrent;
    // old version
    private ContextAction contextActionAerial;
    private ContextAction contextActionGround;

    /**
     * Context's constructor
     * @throws NegativeBudgetException exception if the valut to the budget is negative
     */
    public Context() throws NegativeBudgetException {
        men = 0;
        status = true;
        budget = new Budget(0);
        contracts = new ArrayList<>();
        collectedResources = new HashMap<>();
        completeContract = false;
        firstHead = null;
        heading = null;
        lastDiscovery = null;
        collectedResources = null;

        contextActionCurrent = new ContextAction();
        contextActionAerial = new ContextAction();
        contextActionGround = new ContextAction();
    }

    /**
     * Method to read the string context (json).
     * @param context value of the context of the simulation
     * @throws NegativeBudgetException
     */
    public void read(String context) throws NegativeBudgetException {
        JSONObject jsonObj = new JSONObject(context);

        setMen(jsonObj.getInt("men"));
        setBudget(jsonObj.getInt("budget"));

        JSONArray cont = jsonObj.getJSONArray("contracts");

        for (int i = 0; i < cont.length();  i++) {
            String key = cont.getJSONObject(i).getString("resource");
            int value = cont.getJSONObject(i).getInt("amount");
            addContract(key, value);
        }

        firstHead = Direction.fromString(jsonObj.getString("heading"));
        setHeading(firstHead);
    }

    /**
     * Get the direction of the head
     * @return direction of the head
     */
    public Direction getHeading() {
        return heading;
    }

    /**
     * Get the value of the budget
     * @return value of the budget
     */
    public int getBudget() {
        return budget.remaining();
    }

    /**
     * Get the direction of the head initial
     * @return direction of the head initial
     */
    public Direction getFirstHead() {
        return firstHead;
    }

    /**
     * Get the last discovery
     * @return the last discovery
     */
    public Discovery getLastDiscovery() {
        return lastDiscovery;
    }

    /**
     * Get the status
     * @return status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Get the number of men was gave in the begin of the simulation
     * @return the number of men
     */
    public int getMen() {
        return men;
    }

    /**
     * The the list of contracts gave in the begin of the simulation
     * @return list of contracts
     */
    public List<ContractItem> getContracts() {
        return contracts;
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
     * Method to Remove a quantity of a resource. To use to update after transforme
     * @param resource
     * @param amount
     */
    public void decreaseAmountOfCollectedResources(Resource resource, int amount) {
        if (collectedResources.containsKey(resource.getName())) {
            collectedResources.put(resource.getName(), collectedResources.get(resource.getName()) - amount);
        } else {
           //todo: exception
        }
    }

    /**
     *  Method to return the context current
     * @return context current
     */
    public ContextAction current() {
        return contextActionCurrent;
    }

    /**
     * Set the status value
     * @param s status value
     */
    public void setStatus(boolean s) {
        status = s;
    }

    /**
     * Set the direction of the first head
     * @param direction value of the first head
     */
    public void setFirstHead(Direction direction) {
        firstHead = direction;
    }

    /**
     * Set the value to the budget
     * @param b budget value
     * @throws NegativeBudgetException
     */
    public void setBudget(int b) throws NegativeBudgetException {
        budget = new Budget(b);
    }

    /**
     * Set the number of men
     * @param m number of men
     */
    public void setMen(int m) {
        men = m;
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
            //update the resources manufatured in the list of resources to be create.
           // setResourcesToCreate();
        } catch (Exception ex) {
            contracts.add(new ContractItem(new PrimaryResource(PrimaryType.valueOf(resource)), amount));
        }
    }

    /**
     * Set the direction of the head
     * @param dir direction of the head
     */
    public void setHeading(Direction dir) {
        heading = dir;
    }

    /**
     * Set last discovery
     * @param lastDiscovery last discovery
     */
    public void setLastDiscovery(Discovery lastDiscovery) {
        this.lastDiscovery = lastDiscovery;
    }

    /**
     * Get number of amount of a primaryResource needed to fill the contracts that need of 'resource'(primary + maufactured)
     * @param resource
     * @return
     */
    public int getAcumulatedAmountNecessary(Resource resource){
        int amount = 0;
        for (int i = 0; i < contracts.size(); i++) {
            if ((contracts.get(i).resource() instanceof PrimaryResource)
                && contracts.get(i).resource().getName().equals(resource.getName())) {
                    amount += contracts.get(i).amount();
            } else if (contracts.get(i).resource() instanceof ManufacturedResource
                && ((ManufacturedResource) contracts.get(i).resource()).getRecipe(0).containsKey(resource)) {
                amount += ((ManufacturedResource) contracts.get(i).resource()).getRecipe(contracts.get(i).amount()).get(resource);
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
    public int getQtdToUse(ManufacturedResource manufatured, Resource resource){
        //TODO: Change this method to do verification if the state transform its called because there is enought resource to do all contracts or because the game had to stop
        //Check if resource is parte of manufatured recipe
        if(!manufatured.getRecipe(0).containsKey(resource)){
            //TODO: chage this to trow exception
            return -1;
        }

        int amount = getAcumulatedAmountNecessary(resource);

        for (int i = 0; i < contracts.size(); i++){
            Resource res = contracts.get(i).resource();
            if(res instanceof PrimaryResource){
                if(res.getName().equals(resource.getName()) && contracts.get(i).isComplete(collectedResources)){
                    //Not use amount of a primary resource already complete
                    amount -= contracts.get(i).amount();
                }
            }
            else if(res instanceof ManufacturedResource){
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

    private void setResourcesToCreate(){
        for(int i = 0; i < contracts.size(); i++){
            if ((contracts.get(i).resource()) instanceof ManufacturedResource && !contracts.get(i).isComplete(collectedResources)){
                resourcesToCreate.add((ManufacturedResource) contracts.get(i).resource());
            }
        }
    }

    public void updateToAerial() {
        ContextAction tmpContext = this.contextActionCurrent;
        this.contextActionCurrent = contextActionAerial;
        contextActionGround = tmpContext;
    }

    public void updateToGround() {
        ContextAction tmpContext = this.contextActionCurrent;
        this.contextActionCurrent = contextActionGround;
        contextActionGround = tmpContext;
    }
}
