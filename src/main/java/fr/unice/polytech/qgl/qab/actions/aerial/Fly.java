package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import org.json.JSONObject;

/**
 * Class to represent the action fly.
 *
 * @version 4.9
 */
public class Fly extends ActionAerial {

    public Fly() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.FLY));
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"fly\" }";
    }
}
