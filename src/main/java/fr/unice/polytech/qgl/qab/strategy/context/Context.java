package fr.unice.polytech.qgl.qab.strategy.context;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.utils.Budget;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContextAction;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import static java.lang.Math.ceil;
import fr.unice.polytech.qgl.qab.util.Discovery;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.util.*;

/**
 * @version 16/12/15.
 *
 * Class that represents the context of the simulation.
 */
public class Context {
    private static final Logger LOGGER = LogManager.getLogger(Context.class);
    private static final String ERROR = "error: try to decrese a amount of a resource that was not collected";

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
        resourcesToCreate = null;
        completeContract = false;
        firstHead = null;
        heading = null;
        lastDiscovery = null;

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
            //update the resources manufactured in the list of resources to be create.
            if (resourcesToCreate == null)
                resourcesToCreate = new ArrayList<>();
            resourcesToCreate.add(new ManufacturedResource(ManufacturedType.valueOf(resource)));
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
    public int getAccumulatedAmountNecessary(Resource resource){
        int amount = 0;
        if (!resource.isPrimary()){
            LOGGER.error("Passed the wrong parameter.");
            return -1;
        }
        for (int i = 0; i < contracts.size(); i++) {
            Resource res = contracts.get(i).resource();
            int amountAsked = contracts.get(i).amount();
            if ((res.isPrimary()) && res.getName().equals(resource.getName())) {
                    amount += amountAsked;
            } else if (!(res.isPrimary())){
                PrimaryType prim = ((PrimaryResource)(resource)).getType();
                if (((ManufacturedResource) contracts.get(i).resource()).getRecipe(0).containsKey(prim)){
                    int pureAmount = ((ManufacturedResource) res).getRecipe(contracts.get(i).amount()).get(prim);
                    amount +=  (int) ceil(pureAmount * (((ManufacturedResource)res).getMarginError()));
                }
            }
        }
        //LOGGER.error("The quantity necessary of "+ resource.getName()+ " is " + amount);
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
        LOGGER.error("Enough to transform "+ primaryResources+"");
        return enough;
    }

    /**
     *
     * @return list of primary resources needed to complet all the contracts
     */
    public Set<String> primaryNeeded(){
        Set<String> primaryResource = new HashSet<String>();

        for (int i = 0; i < contracts.size(); i++){
            if (contracts.get(i).resource().isPrimary()){
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
}
