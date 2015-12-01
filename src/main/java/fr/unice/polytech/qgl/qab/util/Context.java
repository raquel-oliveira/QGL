package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.enums.Direction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Context {
    private int men, budget;
    private boolean status;
    private HashMap<String, Integer> contracts;
    private Direction heading;

    public Context() {
        men = 0;
        budget = 0;
        status = true;
        contracts = new HashMap<>();
        heading = null;
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

    public boolean getStatus() {
        return status;
    }

    public int getBudget() {
        return budget;
    }

    public int getMen() {
        return men;
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
}
