package fr.unice.polytech.qgl.qab.engine;

import fr.unice.polytech.qgl.qab.enums.Direction;
import org.json.JSONObject;

/**
 * Abstract class to represent the Action.
 *
 * @version 4.9
 */
public abstract class Action {
    public abstract boolean isValid(JSONObject jsonObj);

    public abstract String makeDecision(Direction head, int budget, Boolean status);
}
