package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * @version 13/12/15.
 */
public class Glimpse extends Action {
    private static final String ACTION_GLIMPSE = "glimpse";

    private int range;

    public Glimpse (Direction direction, int range){
        super();
        this.dirAction = direction;
        this.range = range;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION_PARAMS)) {
            String action = jsonObj.getString(ACTION_PARAMS);
            if (!(ACTION_GLIMPSE).equals(action))
                return false;
        } else {
            return false;
        }

        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"glimpse\", \"parameters\": { \"direction\": \"" + dirAction + "\", \"range\": "+range+" } }";
    }

    @Override
    public Direction getDirection(){
        return this.dirAction;
    }
}