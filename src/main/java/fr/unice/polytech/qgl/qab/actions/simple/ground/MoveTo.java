package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * @version 31/12/15.
 */
public class MoveTo extends Action {
    private static final String ACTION_MOVE_TO = "move_to";

    public MoveTo(Direction dir) {
        super();
        setDirection(dir);
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION_PARAMS)) {
            String action = jsonObj.getString(ACTION_PARAMS);
            if (!(ACTION_MOVE_TO).equals(action))
                return false;
            if(!checkParameter(jsonObj))
                return false;
        }
        else return false;

        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"move_to\", \"parameters\": { \"direction\": \"" + this.dirAction + "\" } }";
    }
}
