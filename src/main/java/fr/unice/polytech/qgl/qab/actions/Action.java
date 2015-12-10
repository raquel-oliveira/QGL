package fr.unice.polytech.qgl.qab.actions;

import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.json.JSONObject;

/**
 * Abstract class to represent the Action.
 *
 * @version 4.9
 */
public interface Action {
    public abstract boolean isValid(JSONObject jsonObj);

    public abstract String formatResponse();
}

