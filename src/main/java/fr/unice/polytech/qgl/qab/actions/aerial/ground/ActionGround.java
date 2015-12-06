package fr.unice.polytech.qgl.qab.actions.aerial.ground;

import fr.unice.polytech.qgl.qab.strategy.Action;
import fr.unice.polytech.qgl.qab.enums.Direction;
import org.json.JSONObject;

/**
 *
 */
public class ActionGround extends Action {
    public boolean isValid(JSONObject jsonObj) {
        return false;
    }

    public String makeDecision(Direction head, int budget, Boolean status) { return null; }
}
