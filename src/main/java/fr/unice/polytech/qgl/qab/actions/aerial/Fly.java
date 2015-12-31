package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 * Class to represent the action fly.
 *
 * @version 4.9
 */
public class Fly implements Action {

    private static final String ACTION_FLY = "fly";

    public Fly() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            String action = jsonObj.getString("action");
            return ACTION_FLY.equals(action);
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"fly\" }";
    }
}
