package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 * Class to represent the action scan.
 *
 * @version 8.12.2016
 */
public class Scan extends Action {
    private static final String ACTION_SCAN = "scan";

    public Scan() {
        super();
    }

    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION)) {
            String action = jsonObj.getString(ACTION);
            return ACTION_SCAN.equals(action);
        }
        return false;
    }

    public String formatResponse() {
        return "{ \"action\": \"scan\" }";
    }
}
