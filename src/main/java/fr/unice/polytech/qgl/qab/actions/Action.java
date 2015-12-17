package fr.unice.polytech.qgl.qab.actions;

import org.json.JSONObject;

/**
 * Abstract class to represent the Action.
 *
 * @version 4.9
 */
public interface Action {
    boolean isValid(JSONObject jsonObj);

    String formatResponse();
}

