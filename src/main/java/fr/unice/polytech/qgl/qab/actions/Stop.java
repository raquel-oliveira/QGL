package fr.unice.polytech.qgl.qab.actions;

import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action stop.
 *
 * @version 4.9
 */
public class Stop extends ActionPlane {
    public Stop() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.STOP));
        }
        return false;
    }

    public boolean canStop(Direction direction, int budget, boolean status) {
        return (rangeOutOfRange(direction) == 0 || budget <= BUDGET_MIN || status == false);
    }
}
