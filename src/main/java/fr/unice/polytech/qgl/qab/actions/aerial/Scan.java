package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 * Class to represent the action scan.
 *
 * @version 8.12.2016
 */
public class Scan implements Action {
    private static final String ACTION_SCAN = "scan";

    public Scan() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            String action = jsonObj.getString("action");
            return ACTION_SCAN.equals(action);
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"scan\" }";
    }
}
