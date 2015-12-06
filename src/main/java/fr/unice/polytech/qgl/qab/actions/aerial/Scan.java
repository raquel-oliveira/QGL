package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import fr.unice.polytech.qgl.qab.enums.Found;
import fr.unice.polytech.qgl.qab.util.Discovery;
import org.json.JSONObject;

/**
 * Class to represent the action scan.
 *
 * @version 4.9
 */
public class Scan extends ActionAerial {
    public Scan() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.SCAN));
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"scan\" }";
    }
}
