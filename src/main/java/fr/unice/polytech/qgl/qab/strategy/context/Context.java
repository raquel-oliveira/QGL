package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.InitializeException;
import fr.unice.polytech.qgl.qab.resources.Resource;
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
    private List<Contract> contracts;

    private Direction first_head;
    private Direction heading;

    private Discovery lastDiscovery;

    public Context() throws InitializeException {
        men = 0;
        status = true;
        budget = new Budget(0);
        contracts = new ArrayList<>();

        first_head = null;
        heading = null;

        lastDiscovery = null;
    }

    public void read(String context) throws InitializeException {
        JSONObject jsonObj = new JSONObject(context);

        setMen(jsonObj.getInt("men"));
        setBudget(jsonObj.getInt("budget"));

        JSONArray cont = jsonObj.getJSONArray("contracts");

        for (int i = 0; i < cont.length();  i++) {
            String key = cont.getJSONObject(i).getString("resource");
            int value = cont.getJSONObject(i).getInt("amount");
            addContract(key, value);
        }

        first_head = Direction.fromString(jsonObj.getString("heading"));
        setHeading(first_head);
    }

    public Direction getHeading() {
        return heading;
    }

    public int getBudget() {
        return budget.remaining();
    }

    public Direction getFirst_head() {
        return first_head;
    }

    public Discovery getLastDiscovery() {
        return lastDiscovery;
    }

    public void setStatus(boolean s) {
        status = s;
    }

    public void setBudget(int b) throws InitializeException {
        try {
            budget = new Budget(b);
        } catch (InitializeException e) {
            budget = new Budget(0);
        }
    }

    public void setMen(int m) {
        men = m;
    }

    public void addContract(String resource, int amount) throws InitializeException {
        contracts.add(new Contract(new Resource(resource), amount));
    }

    public void setHeading(Direction dir) {
        heading = dir;
    }

    public void setLastDiscovery(Discovery lastDiscovery) {
        this.lastDiscovery = lastDiscovery;
    }
}
