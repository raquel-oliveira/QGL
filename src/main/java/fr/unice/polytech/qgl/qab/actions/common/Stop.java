package fr.unice.polytech.qgl.qab.actions.common;

import fr.unice.polytech.qgl.qab.actions.aerial.ActionAerial;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
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

    public static boolean canStop(Direction direction, int budget, boolean status) {
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"stop\" }";
    }
}
