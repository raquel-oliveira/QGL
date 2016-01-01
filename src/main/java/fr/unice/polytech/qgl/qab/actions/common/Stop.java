package fr.unice.polytech.qgl.qab.actions.common;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.strategy.Strategy;
import org.json.JSONObject;

/**
 * Class to represent the action stop.
 *
 * @version 4.9
 */
public class Stop implements Action {
    private static final String ACTION_STOP = "stop";

    public Stop() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            String action = jsonObj.getString("action");
            return ACTION_STOP.equals(action);
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"stop\" }";
    }
}
