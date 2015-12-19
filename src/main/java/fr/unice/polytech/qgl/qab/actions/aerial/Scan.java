package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import org.json.JSONObject;

/**
 * Class to represent the action scan.
 *
 * @version 8.12.2016
 */
public class Scan implements Action {
    public Scan() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return act.isEquals(ActionBot.SCAN);
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"scan\" }";
    }
}
