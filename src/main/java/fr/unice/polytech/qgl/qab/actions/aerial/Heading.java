package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.strategy.Strategy;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action heading.
 *
 * @version 4.9
 */
public class Heading extends Action {
    private static final String ACTION_HEADING = "heading";
    private Direction direction;

    public Heading(Direction dir) {
        super();
        direction = dir;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION)) {
            String action = jsonObj.getString(ACTION);
            if (!(ACTION_HEADING).equals(action)) {
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
        return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

    /**
     * Method to get the direction of the Heading.
     * @return the direction.
     */
    public Direction getDirection() {
        return direction;
    }

}
