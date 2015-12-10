package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action Echo.
 *
 * @version 4.9
 */
public class Echo implements Action {
    private Direction direction;

    public Echo(Direction dir) {
        super();
        direction = dir;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.ECHO));
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

    public Direction getDirection() {
        return direction;
    }
}
