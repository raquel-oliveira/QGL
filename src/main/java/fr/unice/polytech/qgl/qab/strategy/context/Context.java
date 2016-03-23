package fr.unice.polytech.qgl.qab.strategy.context;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.strategy.context.utils.Budget;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContextAction;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.Contracts;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import static java.lang.Math.ceil;
import fr.unice.polytech.qgl.qab.util.Discovery;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 16/12/15.
 *
 * Class that represents the context of the simulation.
 */
public class Context {
    private static final Logger LOGGER = LogManager.getLogger(Context.class);

    private int men;
    private boolean status;
    private Budget budget;
    private Contracts contracts;

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

    private Map<String, Integer> collectedResources;

    /**
     * Context's constructor
     * @throws NegativeBudgetException exception if the valut to the budget is negative
     */
    public Context() throws NegativeBudgetException {
        men = 0;
        status = true;
        budget = new Budget(0);
        contracts = new Contracts();
        firstHead = null;
        heading = null;
        lastDiscovery = null;

        contextActionCurrent = new ContextAction();
        contextActionAerial = new ContextAction();
        contextActionGround = new ContextAction();

        this.collectedResources = new HashMap<>();
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
            contracts.addContract(key, value, this);
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
    public Contracts getContracts() {
        return contracts;
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
