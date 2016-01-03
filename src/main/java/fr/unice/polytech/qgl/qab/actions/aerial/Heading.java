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
public class Heading implements Action {
    private static final String ACTION_HEADING = "heading";
    private static final String ACTION = "action";
    private static final String DIRECTION = "direction";
    private static final String PARAMETERS = "parameters";
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
            if (jsonObj.has(PARAMETERS)) {
                if (jsonObj.getJSONObject(PARAMETERS).has(DIRECTION)) {
                    String dir = jsonObj.getJSONObject(PARAMETERS).getString(DIRECTION);
                    if (Direction.fromString(dir) == null) {
                        return false;
                    }
                } else return false;
            } else return false;
        } else return false;

        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

    public Direction getDirection() {
        return direction;
    }
}
