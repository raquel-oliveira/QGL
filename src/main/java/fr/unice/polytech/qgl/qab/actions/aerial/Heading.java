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
    private Direction direction;

    public Heading(Direction dir) {
        super();
        direction = dir;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            String action = jsonObj.getString("action");
            if (!ACTION_HEADING.equals(action)) return false;
        } else return false;

        if (jsonObj.has("parameters")) {
            if (jsonObj.getJSONObject("parameters").has("direction")) {
                String dir = jsonObj.getJSONObject("parameters").getString("direction");
                if (Direction.fromString(dir) == null)
                    return false;
            }
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

    public Direction getDirection() {
        return direction;
    }
}
