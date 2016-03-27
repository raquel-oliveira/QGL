package fr.unice.polytech.qgl.qab.actions;

import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Abstract class to represent the Action.
 *
 * @version 4.9
 */
public abstract class Action {
    public static final String ACTION_PARAMS = "action";
    public static final String DIRECTION = "direction";
    public static final String PARAMETERS = "parameters";
    protected Direction dirAction;

    /**
     * Method that check if the json is valid
     * @param jsonObj json object to analyze
     * @return true if the json is valid, false if not
     */
    public abstract boolean isValid(JSONObject jsonObj);

    /**
     * Method that return the response in the right structure
     * @return the response in the right structure
     */
    public abstract String formatResponse();

    /**
     * Check parameters if the parameter is the direction
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

    /**
     * Method to get the direction of the Echo and Heading
     * @return the direction.
     */
    public Direction getDirection() {
        return dirAction;
    }

    /**
     * Method to get the direction of the Echo and Heading
     * @return the direction.
     */
    public void setDirection(Direction dir) {
        dirAction = dir;
    }
}
