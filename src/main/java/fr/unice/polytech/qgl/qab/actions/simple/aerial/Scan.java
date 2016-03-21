package fr.unice.polytech.qgl.qab.actions.simple.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 * @version 8.12.2016
 *
 * Class to represent the action scan.
 */
public class Scan extends Action {
    private static final String ACTION_SCAN = "scan";

    public Scan() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION_PARAMS)) {
            String action = jsonObj.getString(ACTION_PARAMS);
            return ACTION_SCAN.equals(action);
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"scan\" }";
    }
}
