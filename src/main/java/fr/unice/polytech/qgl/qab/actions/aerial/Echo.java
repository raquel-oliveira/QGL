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
            return act.isEquals(ActionBot.ECHO);
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

    /**
     * Method to get the direction of the Echo.
     * @return the direction.
     */
    public Direction getDirection() {
        return direction;
    }
}
