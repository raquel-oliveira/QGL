package fr.unice.polytech.qgl.qab.actions.simple.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action Echo.
 *
 * @version 4.9
 *
 * Class that represents the action Echo.
 */
public class Echo extends Action {
    private static final String ACTION_ECHO = "echo";

    /**
     * Echo's constructor
     * @param dir
     */
    public Echo(Direction dir) {
        super();
        setDirection(dir);
    }

    @Override
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

    @Override
    public String formatResponse() {
        return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

}
