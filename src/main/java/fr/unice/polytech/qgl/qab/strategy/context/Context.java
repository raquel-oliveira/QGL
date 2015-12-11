package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.util.enums.Direction;

import fr.unice.polytech.qgl.qab.util.Discovery;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private int men, budget;
    private boolean status;
    private Map<String, Integer> contracts;
    private Direction heading;
    private int width, height;
    private boolean widthDefined, heightDefined;
    private Discovery lastDiscovery;

    public Context() {
        men = 0;
        budget = 0;
        status = true;
        contracts = new HashMap<>();
        heading = null;
        width = height = 0;
        widthDefined = heightDefined = false;
        lastDiscovery = null;
    }

    public void saveContext(String context) {
        JSONObject jsonObj = new JSONObject(context);

        setMen(jsonObj.getInt("men"));
        setBudget(jsonObj.getInt("budget"));

        JSONArray cont = jsonObj.getJSONArray("contracts");

        for (int i = 0; i < cont.length();  i++) {
            String key = cont.getJSONObject(i).getString("resource");
            int value = cont.getJSONObject(i).getInt("amount");
            addContract(key, value);
        }

        setHeading(heading = Direction.fromString(jsonObj.getString("heading")));
    }

    public Direction getHeading() {
        return heading;
    }

    public int getBudget() {
        return budget;
    }

    public void setStatus(boolean s) {
        status = s;
    }

    public void setBudget(int b) {
        budget = b;
    }

    public void setMen(int m) {
        men = m;
    }

    public void addContract(String key, int value) {
        contracts.put(key, value);
    }

    public void setHeading(Direction dir) {
        heading = dir;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isWidthDefined() {
        return widthDefined;
    }

    public void setWidthDefined(boolean defined) {
        widthDefined = defined;
    }

    public boolean isHeightDefined() {
        return heightDefined;
    }

    public void setHeightDefined(boolean defined) {
        heightDefined = defined;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public Discovery getLastDiscovery() {
        return lastDiscovery;
    }

    public void setLastDiscovery(Discovery lastDiscovery) {
        this.lastDiscovery = lastDiscovery;
    }
}
