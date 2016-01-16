package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * @version 31/12/15.
 */
public class MoveTo extends Action {
    private static final String ACTION_MOVE_TO = "move_to";

    private Direction direction;

    public MoveTo(Direction dir) {
        super();
        this.direction = dir;
    }

    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION)) {
            String action = jsonObj.getString(ACTION);
            if (!(ACTION_MOVE_TO).equals(action)) {
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
        return "{ \"action\": \"move_to\", \"parameters\": { \"direction\": \"" + this.direction + "\" } }";
    }
}
