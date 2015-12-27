package fr.unice.polytech.qgl.qab.actions.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action heading.
 *
 * @version 4.9
 */
public class Heading implements Action {
    private Direction direction;

    public Heading(Direction dir) {
        super();
        direction = dir;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            String action = jsonObj.getString("action");
            if (!("heading").equals(action))
                return false;
        } else return false;

        if (jsonObj.has("parameters")) {
            if (!jsonObj.getJSONObject("parameters").has("direction")) {
                return false;
            }
        } else return false;
        return true;
    }

    //TODO: let direction or direction.toString() ?
    @Override
    public String formatResponse() {
        return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"" + direction.toString() + "\" } }";
    }

    public Direction getDirection() {
        return direction;
    }
}
