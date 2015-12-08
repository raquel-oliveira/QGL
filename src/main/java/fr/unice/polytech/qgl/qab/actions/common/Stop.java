package fr.unice.polytech.qgl.qab.actions.common;

import fr.unice.polytech.qgl.qab.actions.aerial.ActionAerial;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action stop.
 *
 * @version 4.9
 */
public class Stop extends ActionAerial {
    public Stop() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.STOP));
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"stop\" }";
    }
}
