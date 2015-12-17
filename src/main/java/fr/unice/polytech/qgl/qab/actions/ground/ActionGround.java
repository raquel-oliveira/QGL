package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 *
 */
public class ActionGround implements Action {
    @Override
    public boolean isValid(JSONObject jsonObj) {
        return false;
    }

    @Override
    public String formatResponse() {
        return null;
    }
}
