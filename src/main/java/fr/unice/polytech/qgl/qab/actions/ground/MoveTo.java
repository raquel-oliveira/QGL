package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * @version 31/12/15.
 */
public class MoveTo implements Action {
    private Direction direction;

    public MoveTo(Direction dir) {
        super();
        this.direction = dir;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"move_to\", \"parameters\": { \"direction\": \"" + this.direction + "\" } }";
    }
}
