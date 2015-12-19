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
    private Budget budget;
    private int men;
    private boolean status;
    private List<Contract> contracts;
    private Direction heading;
    private int width, height;
    private boolean widthDefined, heightDefined;
    private Discovery lastDiscovery;
    private Direction first_head;

    public Context() {
        men = 0;
        status = true;
        contracts = new ArrayList<>();
        heading = null;
        width = height = 0;
        widthDefined = heightDefined = false;
        lastDiscovery = null;
        first_head = null;
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

    public void setStatus(boolean s) {
        status = s;
    }

    public void setBudget(int b) throws InitializeException {
        budget = Budget.getInstance(b, b);
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

    public Discovery getLastDiscovery() {
        return lastDiscovery;
    }

    public void setLastDiscovery(Discovery lastDiscovery) {
        this.lastDiscovery = lastDiscovery;
    }

    public Direction getFirst_head() {
        return first_head;
    }
}
