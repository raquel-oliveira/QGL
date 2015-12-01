package fr.unice.polytech.qgl.qab.actions;

import org.json.JSONObject;

/**
 * Abstract class to represent the Action.
 *
 * @version 4.9
 */
public abstract class Action {
    public boolean isValid(JSONObject jsonObj) {
        return false;
    }

    public Action makeDecision() {
        return null;
    }
}
