package fr.unice.polytech.qgl.qab.actions.simple.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * @version 4.9
 *
 * Class to represent the action heading.
 */
public class Heading extends Action {
    private static final String ACTION_HEADING = "heading";

    public Heading(Direction dir) {
        super();
        setDirection(dir);
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION_PARAMS)) {
            String action = jsonObj.getString(ACTION_PARAMS);
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
        return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"" + dirAction + "\" } }";
    }
}
