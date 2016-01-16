package fr.unice.polytech.qgl.qab.actions;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Abstract class to represent the Action.
 *
 * @version 4.9
 */
public abstract class Action {
    public static final String ACTION = "action";
    public static final String DIRECTION = "direction";
    public static final String PARAMETERS = "parameters";

    public abstract boolean isValid(JSONObject jsonObj);

    public abstract String formatResponse();

    /**
     *Check parameters if the parameter is the direction
     */
    public boolean checkParameter(JSONObject jsonObj){
        if (jsonObj.has(PARAMETERS)) {
            if (jsonObj.getJSONObject(PARAMETERS).has(DIRECTION)) {
                String dir = jsonObj.getJSONObject(PARAMETERS).getString(DIRECTION);
                return !(Direction.fromString(dir) == null);
            } else return false;
        }
        else return false;
    }
}
