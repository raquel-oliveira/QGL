package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import fr.unice.polytech.qgl.qab.util.Discovery;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private int men;
    private boolean status;
    private Budget budget;
    private List<ContractItem> contracts;

    private Direction firstHead;
    private Direction heading;

    private Discovery lastDiscovery;

    public Context() throws NegativeBudgetException {
        men = 0;
        status = true;
        budget = new Budget(0);
        contracts = new ArrayList<>();

        firstHead = null;
        heading = null;

        lastDiscovery = null;
    }

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

    public Direction getHeading() {
        return heading;
    }

    public int getBudget() {
        return budget.remaining();
    }

    public Direction getFirstHead() {
        return firstHead;
    }

    public Discovery getLastDiscovery() {
        return lastDiscovery;
    }

    public boolean getStatus() {
        return status;
    }

    public int getMen() {
        return men;
    }

    public void setStatus(boolean s) {
        status = s;
    }

    public void setFirstHead(Direction direction) {
        firstHead = direction;
    }

    public void setBudget(int b) throws NegativeBudgetException {
        budget = new Budget(b);
    }

    public void setMen(int m) {
        men = m;
    }

    public void addContract(String resource, int amount) throws NegativeBudgetException {
        try {
            contracts.add(new ContractItem(new ManufacturedResource(ManufacturedType.valueOf(resource)), amount));
        } catch (Exception ex) {
            contracts.add(new ContractItem(new PrimaryResource(PrimaryType.valueOf(resource)), amount));
        }
    }

    public void setHeading(Direction dir) {
        heading = dir;
    }

    public void setLastDiscovery(Discovery lastDiscovery) {
        this.lastDiscovery = lastDiscovery;
    }
}
