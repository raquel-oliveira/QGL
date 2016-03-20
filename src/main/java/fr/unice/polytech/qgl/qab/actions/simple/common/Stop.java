package fr.unice.polytech.qgl.qab.actions.simple.common;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 * Class to represent the action stop.
 *
 * @version 4.9
 */
public class Stop extends Action {
    private static final String ACTION_STOP = "stop";

    public Stop() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION_PARAMS)) {
            String action = jsonObj.getString(ACTION_PARAMS);
            return ACTION_STOP.equals(action);
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"stop\" }";
    }
}
