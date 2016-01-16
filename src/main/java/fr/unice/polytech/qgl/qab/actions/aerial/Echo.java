package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action Echo.
 *
 * @version 4.9
 */
public class Echo extends Action {
    private static final String ACTION_ECHO = "echo";

    private Direction direction;

    public Echo(Direction dir) {
        super();
        direction = dir;
    }

    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION)) {
            String action = jsonObj.getString(ACTION);
            if (!(ACTION_ECHO).equals(action)) {
                return false;
            }
            if(!checkParameter(jsonObj)) {
                return false;
            }
        }
        else return false;

        return true;
    }

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
