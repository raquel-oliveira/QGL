package fr.unice.polytech.qgl.qab.engine.ground;

import fr.unice.polytech.qgl.qab.engine.Action;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
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
