package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 * Class to represent the action fly.
 *
 * @version 4.9
 */
public class Fly extends Action {

    private static final String ACTION_FLY = "fly";

    public Fly() {
        super();
    }

    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION)) {
            String action = jsonObj.getString(ACTION);
            return ACTION_FLY.equals(action);
        }
        return false;
    }

    public String formatResponse() {
        return "{ \"action\": \"fly\" }";
    }
}
